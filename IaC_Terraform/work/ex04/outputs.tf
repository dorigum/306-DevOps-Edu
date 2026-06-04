output "vpc_id" {
  description = "ID of the created VPC."
  value       = aws_vpc.main.id
}

output "docker_public_ip" {
  description = "Public IP address of the Docker instance."
  value       = aws_instance.docker.public_ip
}

output "bastion_public_ip" {
  description = "Public IP address of the Bastion instance."
  value       = aws_instance.bastion.public_ip
}

output "internal_private_ip" {
  description = "Private IP address of the Internal instance."
  value       = aws_instance.internal.private_ip
}

output "private_key_path" {
  description = "Local path of the generated private key."
  value       = local_sensitive_file.kosta_private_key.filename
}
