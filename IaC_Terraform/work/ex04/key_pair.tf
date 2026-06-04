resource "tls_private_key" "kosta" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_key_pair" "kosta" {
  key_name   = "kosta-ex04"
  public_key = tls_private_key.kosta.public_key_openssh

  tags = merge(local.common_tags, {
    Name = "kosta"
  })
}

resource "local_sensitive_file" "kosta_private_key" {
  filename        = pathexpand("~/.ssh/kosta-ex04.pem")
  content         = tls_private_key.kosta.private_key_pem
  file_permission = "0400"
}