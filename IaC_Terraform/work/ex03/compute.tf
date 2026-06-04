# SSH 키쌍 생성
# 1) RSA 프라이빗 키 생성
resource "tls_private_key" "kosta_key" {
  algorithm = "RSA"
  rsa_bits  = 2048
}

# 2) AWS에 공개 키 등록
resource "aws_key_pair" "kosta_keypair" {
  key_name   = var.key_pair_name
  public_key = tls_private_key.kosta_key.public_key_openssh

  tags = {
    Name = "${var.project_name}-keypair"
  }
}

# 3) 프라이빗 키를 로컬 파일로 저장
resource "local_file" "kosta_pem" {
  filename        = "${pathexpand("~")}/.ssh/${var.key_pair_name}.pem"
  content         = tls_private_key.kosta_key.private_key_pem
  file_permission = "0600"

  depends_on = [tls_private_key.kosta_key]
}

# AMI 데이터 소스 (Amazon Linux 2023)
data "aws_ami" "amazon_linux_2023" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-*"]
  }

  filter {
    name   = "root-device-type"
    values = ["ebs"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

# Docker 설치 스크립트
locals {
  docker_userdata = base64encode(<<-EOF
              #!/bin/bash
              set -e
              
              # Docker 설치
              sudo dnf install -y docker
              
              # Docker 서비스 자동 시작 및 활성화
              sudo systemctl enable docker --now
              
              # ec2-user 사용자에게 docker 권한 부여
              sudo usermod -aG docker ec2-user
              EOF
  )
}

# Bastion Host (Docker 포함) - kosta-public01
resource "aws_instance" "bastion_docker" {
  ami                    = data.aws_ami.amazon_linux_2023.id
  instance_type          = var.instance_type
  subnet_id              = aws_subnet.public_subnets[0].id
  vpc_security_group_ids = [aws_security_group.bastion_sg.id]
  key_name               = aws_key_pair.kosta_keypair.key_name
  
  user_data = local.docker_userdata

  tags = {
    Name = "kosta-public01-docker"
    Role = "BastionDocker"
  }

  depends_on = [
    aws_key_pair.kosta_keypair,
    aws_security_group.bastion_sg,
    local_file.kosta_pem
  ]
}

# Bastion Host (점프호스트) - kosta-public02
resource "aws_instance" "bastion_jump" {
  ami                    = data.aws_ami.amazon_linux_2023.id
  instance_type          = var.instance_type
  subnet_id              = aws_subnet.public_subnets[1].id
  vpc_security_group_ids = [aws_security_group.bastion_sg.id]
  key_name               = aws_key_pair.kosta_keypair.key_name

  tags = {
    Name = "kosta-public02-bastion"
    Role = "BastionJump"
  }

  depends_on = [
    aws_key_pair.kosta_keypair,
    aws_security_group.bastion_sg,
    local_file.kosta_pem
  ]
}

# Internal Instance - kosta-private01
resource "aws_instance" "internal_app" {
  ami                    = data.aws_ami.amazon_linux_2023.id
  instance_type          = var.instance_type
  subnet_id              = aws_subnet.private_subnets[0].id
  vpc_security_group_ids = [aws_security_group.internal_sg.id]
  key_name               = aws_key_pair.kosta_keypair.key_name

  tags = {
    Name = "kosta-private01-internal"
    Role = "InternalApp"
  }

  depends_on = [
    aws_key_pair.kosta_keypair,
    aws_security_group.internal_sg,
    aws_nat_gateway.kosta_nat_gw,
    local_file.kosta_pem
  ]
}

# Internal Instance - kosta-private02 (예비)
resource "aws_instance" "internal_backup" {
  ami                    = data.aws_ami.amazon_linux_2023.id
  instance_type          = var.instance_type
  subnet_id              = aws_subnet.private_subnets[1].id
  vpc_security_group_ids = [aws_security_group.internal_sg.id]
  key_name               = aws_key_pair.kosta_keypair.key_name

  tags = {
    Name = "kosta-private02-backup"
    Role = "InternalBackup"
  }

  depends_on = [
    aws_key_pair.kosta_keypair,
    aws_security_group.internal_sg,
    aws_nat_gateway.kosta_nat_gw,
    local_file.kosta_pem
  ]
}

# SSH 키 정보 출력
output "ssh_key_path" {
  description = "SSH 프라이빗 키 경로"
  value       = local_file.kosta_pem.filename
  sensitive   = true
}

output "key_pair_name" {
  description = "AWS KeyPair 이름"
  value       = aws_key_pair.kosta_keypair.key_name
}
