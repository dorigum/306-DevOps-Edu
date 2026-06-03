resource "aws_vpc" "terraform-vpc" {
  cidr_block           = "10.250.0.0/16"
  enable_dns_support   = true  # true 로 해야 ALB와RDS 설치할 수 있음
  enable_dns_hostnames = true  # true 로 해야 ALB와RDS 설치할 수 있음
  tags = {
    "Name" = "terraform-vpc"
  }
}
