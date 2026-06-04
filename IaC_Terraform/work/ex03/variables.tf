variable "aws_region" {
  description = "AWS 리전"
  default     = "ap-northeast-2"
}

variable "vpc_cidr" {
  description = "VPC CIDR 블록"
  default     = "172.16.0.0/16"
}

variable "instance_type" {
  description = "EC2 인스턴스 타입"
  default     = "t2.micro"
}

variable "environment" {
  description = "환경명"
  default     = "dev"
}

variable "key_pair_name" {
  description = "AWS KeyPair 이름"
  default     = "kosta"
}

variable "project_name" {
  description = "프로젝트명"
  default     = "kosta"
}

# 퍼블릭 서브넷 설정
variable "public_subnet_cidrs" {
  description = "퍼블릭 서브넷 CIDR 블록"
  type        = list(string)
  default     = ["172.16.0.0/24", "172.16.10.0/24"]
}

# 프라이빗 서브넷 설정
variable "private_subnet_cidrs" {
  description = "프라이빗 서브넷 CIDR 블록"
  type        = list(string)
  default     = ["172.16.100.0/24", "172.16.110.0/24"]
}

# 가용 영역
variable "availability_zones" {
  description = "AWS 가용 영역"
  type        = list(string)
  default     = ["ap-northeast-2a", "ap-northeast-2c"]
}

variable "subnet_names" {
  description = "서브넷 이름"
  type        = list(string)
  default     = ["kosta-public01", "kosta-public02", "kosta-private01", "kosta-private02"]
}
