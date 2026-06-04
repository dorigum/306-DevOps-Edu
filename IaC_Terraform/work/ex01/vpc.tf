# Create a VPC
resource "aws_vpc" "kosta-vpc" {
  cidr_block = "172.16.0.0/16"
  tags = {
    "Name" = "kosta-vpc"
  }
}

resource "aws_subnet" "kosta-public01" {
    vpc_id = aws_vpc.kosta-vpc.id
    cidr_block = "172.16.0.0/24"
    tags = {
      "Name" ="kosta-public01"
    }
    availability_zone ="ap-northeast-2a" 
}