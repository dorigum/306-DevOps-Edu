# Terraform Provider 설정
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
    tls = {
      source  = "hashicorp/tls"
      version = "~> 4.0"
    }
    local = {
      source  = "hashicorp/local"
      version = "~> 2.0"
    }
  }
  required_version = ">= 1.0"
}

provider "aws" {
  region = var.aws_region

  default_tags {
    tags = {
      Environment = var.environment
      Project     = var.project_name
      ManagedBy   = "Terraform"
    }
  }
}

# VPC 생성
resource "aws_vpc" "kosta_vpc" {
  cidr_block           = var.vpc_cidr
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "${var.project_name}-VPC"
  }
}

# Internet Gateway 생성
resource "aws_internet_gateway" "kosta_igw" {
  vpc_id = aws_vpc.kosta_vpc.id

  tags = {
    Name = "${var.project_name}-IGW"
  }

  depends_on = [aws_vpc.kosta_vpc]
}

# Elastic IP for NAT Gateway (퍼블릭 서브넷 1)
resource "aws_eip" "nat_eip" {
  domain = "vpc"

  tags = {
    Name = "${var.project_name}-NAT-EIP"
  }

  depends_on = [aws_internet_gateway.kosta_igw]
}

# NAT Gateway (퍼블릭 서브넷 1)
resource "aws_nat_gateway" "kosta_nat_gw" {
  allocation_id = aws_eip.nat_eip.id
  subnet_id     = aws_subnet.public_subnets[0].id

  tags = {
    Name = "${var.project_name}-NAT-GW"
  }

  depends_on = [aws_internet_gateway.kosta_igw]
}

# 퍼블릭 서브넷 생성
resource "aws_subnet" "public_subnets" {
  count             = 2
  vpc_id            = aws_vpc.kosta_vpc.id
  cidr_block        = var.public_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]

  map_public_ip_on_launch = true

  tags = {
    Name = var.subnet_names[count.index]
    Type = "Public"
  }

  depends_on = [aws_vpc.kosta_vpc]
}

# 프라이빗 서브넷 생성
resource "aws_subnet" "private_subnets" {
  count             = 2
  vpc_id            = aws_vpc.kosta_vpc.id
  cidr_block        = var.private_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]

  map_public_ip_on_launch = false

  tags = {
    Name = var.subnet_names[count.index + 2]
    Type = "Private"
  }

  depends_on = [aws_vpc.kosta_vpc]
}

# 퍼블릭 라우팅 테이블
resource "aws_route_table" "public_rt" {
  vpc_id = aws_vpc.kosta_vpc.id

  route {
    cidr_block      = "0.0.0.0/0"
    gateway_id      = aws_internet_gateway.kosta_igw.id
  }

  tags = {
    Name = "${var.project_name}-public-RT"
  }

  depends_on = [aws_internet_gateway.kosta_igw]
}

# 퍼블릭 라우팅 테이블 연결
resource "aws_route_table_association" "public_rt_assoc" {
  count          = 2
  subnet_id      = aws_subnet.public_subnets[count.index].id
  route_table_id = aws_route_table.public_rt.id
}

# 프라이빗 라우팅 테이블
resource "aws_route_table" "private_rt" {
  vpc_id = aws_vpc.kosta_vpc.id

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.kosta_nat_gw.id
  }

  tags = {
    Name = "${var.project_name}-private-RT"
  }

  depends_on = [aws_nat_gateway.kosta_nat_gw]
}

# 프라이빗 라우팅 테이블 연결
resource "aws_route_table_association" "private_rt_assoc" {
  count          = 2
  subnet_id      = aws_subnet.private_subnets[count.index].id
  route_table_id = aws_route_table.private_rt.id
}
