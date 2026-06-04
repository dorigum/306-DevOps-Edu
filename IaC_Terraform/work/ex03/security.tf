# Bastion Security Group
resource "aws_security_group" "bastion_sg" {
  name        = "${var.project_name}-bastion-sg"
  description = "Bastion Host Security Group - Allow SSH access"
  vpc_id      = aws_vpc.kosta_vpc.id

  tags = {
    Name = "${var.project_name}-Bastion-SG"
  }

  depends_on = [aws_vpc.kosta_vpc]
}

# Bastion Inbound Rule - SSH from anywhere
resource "aws_vpc_security_group_ingress_rule" "bastion_ssh" {
  security_group_id = aws_security_group.bastion_sg.id

  description = "Allow SSH from anywhere"
  from_port   = 22
  to_port     = 22
  ip_protocol = "tcp"
  cidr_ipv4   = "0.0.0.0/0"

  tags = {
    Name = "Allow SSH"
  }
}

# Bastion Outbound Rule - All traffic
resource "aws_vpc_security_group_egress_rule" "bastion_outbound" {
  security_group_id = aws_security_group.bastion_sg.id

  description = "Allow all outbound traffic"
  from_port   = -1
  to_port     = -1
  ip_protocol = "-1"
  cidr_ipv4   = "0.0.0.0/0"

  tags = {
    Name = "Allow All Outbound"
  }
}

# Internal Security Group
resource "aws_security_group" "internal_sg" {
  name        = "${var.project_name}-internal-sg"
  description = "Internal Instance Security Group - Allow SSH from Bastion only"
  vpc_id      = aws_vpc.kosta_vpc.id

  tags = {
    Name = "${var.project_name}-Internal-SG"
  }

  depends_on = [aws_vpc.kosta_vpc]
}

# Internal Inbound Rule - SSH from Bastion only
resource "aws_vpc_security_group_ingress_rule" "internal_ssh_from_bastion" {
  security_group_id = aws_security_group.internal_sg.id

  description              = "Allow SSH from Bastion"
  from_port                = 22
  to_port                  = 22
  ip_protocol              = "tcp"
  referenced_security_group_id = aws_security_group.bastion_sg.id

  tags = {
    Name = "Allow SSH from Bastion"
  }
}

# Internal Inbound Rule - All traffic from Internal SG
resource "aws_vpc_security_group_ingress_rule" "internal_from_internal" {
  security_group_id = aws_security_group.internal_sg.id

  description              = "Allow all traffic from Internal SG"
  from_port                = -1
  to_port                  = -1
  ip_protocol              = "-1"
  referenced_security_group_id = aws_security_group.internal_sg.id

  tags = {
    Name = "Allow All from Internal"
  }
}

# Internal Outbound Rule - All traffic
resource "aws_vpc_security_group_egress_rule" "internal_outbound" {
  security_group_id = aws_security_group.internal_sg.id

  description = "Allow all outbound traffic"
  from_port   = -1
  to_port     = -1
  ip_protocol = "-1"
  cidr_ipv4   = "0.0.0.0/0"

  tags = {
    Name = "Allow All Outbound"
  }
}
