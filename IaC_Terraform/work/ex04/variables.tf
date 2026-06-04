variable "aws_region" {
  description = "AWS region to create resources in."
  type        = string
  default     = "ap-northeast-2"
}

variable "admin_ssh_cidr" {
  description = "CIDR block allowed to SSH into public instances."
  type        = string
  default     = "0.0.0.0/0"
}

variable "instance_type" {
  description = "EC2 instance type for all instances."
  type        = string
  default     = "t3.medium"
}

locals {
  project_name = "kosta"

  common_tags = {
    Project = local.project_name
  }
}
