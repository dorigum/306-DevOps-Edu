resource "aws_subnet" "public01" {
  vpc_id                  = aws_vpc.main.id
  cidr_block              = "172.16.0.0/24"
  availability_zone       = "ap-northeast-2a"
  map_public_ip_on_launch = true

  tags = merge(local.common_tags, {
    Name = "kosta-public01"
  })
}

resource "aws_subnet" "public02" {
  vpc_id                  = aws_vpc.main.id
  cidr_block              = "172.16.10.0/24"
  availability_zone       = "ap-northeast-2c"
  map_public_ip_on_launch = true

  tags = merge(local.common_tags, {
    Name = "kosta-public02"
  })
}

resource "aws_subnet" "private01" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "172.16.100.0/24"
  availability_zone = "ap-northeast-2a"

  tags = merge(local.common_tags, {
    Name = "kosta-private01"
  })
}

resource "aws_subnet" "private02" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "172.16.110.0/24"
  availability_zone = "ap-northeast-2c"

  tags = merge(local.common_tags, {
    Name = "kosta-private02"
  })
}
