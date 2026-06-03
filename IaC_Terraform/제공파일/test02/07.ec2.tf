# Ubuntu 22.04 LTS (jammy) 최신 AMI 자동 조회
data "aws_ami" "ubuntu_2204" {
  most_recent = true
  owners      = ["099720109477"] # Canonical(우분투 공식)

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

#  FRONT EC2
resource "aws_instance" "terraform-pub-ec2-front-2a" {
  ami                         = data.aws_ami.ubuntu_2204.id
  instance_type               = "t3.small"
  vpc_security_group_ids      = [aws_security_group.terraform-sg-bastion.id]
  subnet_id                   = aws_subnet.terraform-pub-subnet-2a.id
  key_name                    = "my-edu"
  associate_public_ip_address = true

  root_block_device {
    volume_size = 8
    volume_type = "gp2"

    tags = {
      Name = "terraform-pub-ec2-front-2a"
    }
  }

  tags = {
    Name = "terraform-pub-ec2-front-2a"
  }
}
