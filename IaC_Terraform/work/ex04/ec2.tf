data "aws_ami" "amazon_linux_2023" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-2023.*-x86_64"]
  }

  filter {
    name   = "architecture"
    values = ["x86_64"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

resource "aws_instance" "docker" {
  ami                         = data.aws_ami.amazon_linux_2023.id
  instance_type               = var.instance_type
  subnet_id                   = aws_subnet.public01.id
  vpc_security_group_ids      = [aws_security_group.docker.id]
  key_name                    = aws_key_pair.kosta.key_name
  associate_public_ip_address = true

  user_data = <<-EOF
              #!/bin/bash
              sudo dnf install -y docker
              sudo systemctl enable docker --now
              sudo usermod -aG docker ec2-user
              EOF

  tags = merge(local.common_tags, {
    Name = "docker"
  })
}

resource "aws_instance" "bastion" {
  ami                         = data.aws_ami.amazon_linux_2023.id
  instance_type               = var.instance_type
  subnet_id                   = aws_subnet.public02.id
  vpc_security_group_ids      = [aws_security_group.bastion.id]
  key_name                    = aws_key_pair.kosta.key_name
  associate_public_ip_address = true

  tags = merge(local.common_tags, {
    Name = "bastion"
  })
}

resource "aws_instance" "internal" {
  ami                    = data.aws_ami.amazon_linux_2023.id
  instance_type          = var.instance_type
  subnet_id              = aws_subnet.private01.id
  vpc_security_group_ids = [aws_security_group.internal.id]
  key_name               = aws_key_pair.kosta.key_name

  tags = merge(local.common_tags, {
    Name = "Internal"
  })
}
