# Public Subnet
resource "aws_subnet" "terraform-pub-subnet-2a" {
  vpc_id                  = aws_vpc.terraform-vpc.id
  cidr_block              = "10.250.1.0/24"
  availability_zone       = "ap-northeast-2a"
  map_public_ip_on_launch = "true"
  tags = {
    "Name"                              = "terraform-pub-subnet-2a"
    "kubernetes.io/role/elb"            = "1"       # tag 가 각 2개가 쌍으로 들어가있어야 public에 EKS 가 생성됨
    "kubernetes.io/cluster/terraform-eks-cluster" = "shared"
  }
}


#  ALB 를 위해 추가할 2번째 Public Subnet (다른 AZ)
resource "aws_subnet" "terraform-pub-subnet-2c" {
  vpc_id                  = aws_vpc.terraform-vpc.id
  cidr_block              = "10.250.2.0/24"      # VPC 10.250.0.0/16 안에서 안 겹치는 대역
  availability_zone       = "ap-northeast-2c"   # 다른 AZ
  map_public_ip_on_launch = true
  tags = {
    "Name"                              = "terraform-pub-subnet-2c"
    "kubernetes.io/role/elb"            = "1"
    "kubernetes.io/cluster/terraform-eks-cluster" = "shared"
  }
}