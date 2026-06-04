# VPC 정보
output "vpc_id" {
  description = "VPC ID"
  value       = aws_vpc.kosta_vpc.id
}

output "vpc_cidr_block" {
  description = "VPC CIDR 블록"
  value       = aws_vpc.kosta_vpc.cidr_block
}

# 서브넷 정보
output "public_subnet_ids" {
  description = "퍼블릭 서브넷 IDs"
  value = {
    "kosta-public01" = aws_subnet.public_subnets[0].id
    "kosta-public02" = aws_subnet.public_subnets[1].id
  }
}

output "private_subnet_ids" {
  description = "프라이빗 서브넷 IDs"
  value = {
    "kosta-private01" = aws_subnet.private_subnets[0].id
    "kosta-private02" = aws_subnet.private_subnets[1].id
  }
}

# Internet Gateway 정보
output "internet_gateway_id" {
  description = "Internet Gateway ID"
  value       = aws_internet_gateway.kosta_igw.id
}

# NAT Gateway 정보
output "nat_gateway_id" {
  description = "NAT Gateway ID"
  value       = aws_nat_gateway.kosta_nat_gw.id
}

output "nat_gateway_public_ip" {
  description = "NAT Gateway Elastic IP"
  value       = aws_eip.nat_eip.public_ip
}

# 보안 그룹 정보
output "bastion_security_group_id" {
  description = "Bastion Security Group ID"
  value       = aws_security_group.bastion_sg.id
}

output "internal_security_group_id" {
  description = "Internal Security Group ID"
  value       = aws_security_group.internal_sg.id
}

# Bastion Host (Docker) 정보
output "bastion_docker_instance_id" {
  description = "Bastion Docker 인스턴스 ID"
  value       = aws_instance.bastion_docker.id
}

output "bastion_docker_public_ip" {
  description = "Bastion Docker 인스턴스 공인 IP (SSH 접속용)"
  value       = aws_instance.bastion_docker.public_ip
}

output "bastion_docker_private_ip" {
  description = "Bastion Docker 인스턴스 프라이빗 IP"
  value       = aws_instance.bastion_docker.private_ip
}

# Bastion Host (점프호스트) 정보
output "bastion_jump_instance_id" {
  description = "Bastion 점프호스트 인스턴스 ID"
  value       = aws_instance.bastion_jump.id
}

output "bastion_jump_public_ip" {
  description = "Bastion 점프호스트 공인 IP (SSH 접속용)"
  value       = aws_instance.bastion_jump.public_ip
}

output "bastion_jump_private_ip" {
  description = "Bastion 점프호스트 프라이빗 IP"
  value       = aws_instance.bastion_jump.private_ip
}

# Internal Instance 정보
output "internal_app_instance_id" {
  description = "Internal 앱 인스턴스 ID"
  value       = aws_instance.internal_app.id
}

output "internal_app_private_ip" {
  description = "Internal 앱 인스턴스 프라이빗 IP"
  value       = aws_instance.internal_app.private_ip
}

output "internal_backup_instance_id" {
  description = "Internal 백업 인스턴스 ID"
  value       = aws_instance.internal_backup.id
}

output "internal_backup_private_ip" {
  description = "Internal 백업 인스턴스 프라이빗 IP"
  value       = aws_instance.internal_backup.private_ip
}

# SSH 키 정보
output "ssh_key_pair_name" {
  description = "AWS KeyPair 이름"
  value       = aws_key_pair.kosta_keypair.key_name
}

output "ssh_private_key_path" {
  description = "SSH 프라이빗 키 로컬 경로"
  value       = local_file.kosta_pem.filename
}

# 접속 정보 요약
output "connection_info" {
  description = "SSH 접속 정보"
  value = {
    "Bastion Docker 접속" : "ssh -i ${local_file.kosta_pem.filename} ec2-user@${aws_instance.bastion_docker.public_ip}"
    "Bastion 점프호스트 접속" : "ssh -i ${local_file.kosta_pem.filename} ec2-user@${aws_instance.bastion_jump.public_ip}"
    "Internal 앱 (경유 접속)" : "ssh -i ${local_file.kosta_pem.filename} -J ec2-user@${aws_instance.bastion_jump.public_ip} ec2-user@${aws_instance.internal_app.private_ip}"
    "Docker 설치 확인" : "ssh -i ${local_file.kosta_pem.filename} ec2-user@${aws_instance.bastion_docker.public_ip} 'docker version'"
  }
}

# AMI 정보
output "instance_ami_id" {
  description = "EC2 인스턴스에 사용된 AMI ID"
  value       = data.aws_ami.amazon_linux_2023.id
}

output "instance_ami_name" {
  description = "EC2 인스턴스에 사용된 AMI 이름"
  value       = data.aws_ami.amazon_linux_2023.name
}
