# AWS VPC & Bastion Host Infrastructure Blueprint

이 프로젝트는 Terraform을 사용하여 고가용성과 보안성을 갖춘 AWS VPC 인프라 및 베스천 호스트(Bastion Host) 환경을 자동으로 구축하는 가이드라인을 제공합니다. 

제공된 아키텍처 다이어그램을 기반으로 설계되었으며, 외부 인터넷에서 프라이빗 서브넷 내부에 위치한 내부 인스턴스(Internal Instance)에 안전하게 SSH 등으로 원격 접속할 수 있는 환경을 구성합니다.

---

## 🏗️ 아키텍처 개요 (Architecture Overview)

전체 네트워크 아키텍처는 서울 리전(`ap-northeast-2`) 내의 2개 가용 영역(AZ)에 걸쳐 복수 서브넷 레이어로 분리되어 보안성을 극대화했습니다.

### 1. 기본 네트워크 구성 (VPC & Internet Gateway)
* **VPC Name:** `kosta-VPC`
* **CIDR Block:** `172.16.0.0/16`
* **인터넷 게이트웨이 (IGW):** 퍼블릭 서브넷의 외부 통신 및 외부에서의 베스천 호스트 접속을 위해 VPC에 연결됩니다.

### 2. 가용 영역 및 서브넷 설계 (Subnet Architecture)

| 가용 영역 (AZ) | 서브넷 이름 | 구분 | CIDR 블록 | 주요 리소스 / 역할 |
| :--- | :--- | :--- | :--- | :--- |
| **ap-northeast-2a** | `kosta-public01` | Public | `172.16.0.0/24` | Docker 컨테이너 호스트 등 외부 노출 가능 서비스 |
| **ap-northeast-2a** | `kosta-private01` | Private | `172.16.100.0/24` | **Internal 인스턴스** (외부 직접 접근 차단, 내부 백엔드/DB) |
| **ap-northeast-2c** | `kosta-public02` | Public | `172.16.10.0/24` | **Bastion Host (점프호스트)** |
| **ap-northeast-2c** | `kosta-private02` | Private | `172.16.110.0/24` | 예비 프라이빗 비즈니스 로직 영역 |

### 3. 핵심 접근 메커니즘 (Bastion Jump Host)
* **점프호스트 (Bastion Server):** 외부 인터넷에서 내부 프라이빗 리소스에 보안 접속(SSH 등)하기 위해 필수적인 관문 역할을 수행합니다. `kosta-public02` 서브넷에 배치됩니다.
* **접속 흐름:** 외부 사용자 컴퓨터 ➔ 인터넷(IGW) ➔ Bastion Host (`kosta-public02`) ➔ Internal 인스턴스 (`kosta-private01`)

---

## � 아키텍처 다이어그램 (Network Diagram)

![Terraform AWS VPC Architecture](./terraform.png)

위 다이어그램은 본 프로젝트의 전체 네트워크 구조를 시각적으로 나타냅니다:
- **Public Subnets (퍼블릭 서브넷):** Bastion Host와 Docker 호스트 배치
- **Private Subnets (프라이빗 서브넷):** Internal 인스턴스 및 백엔드 서비스 배치
- **Internet Gateway (IGW):** 외부 인터넷 통신 관문
- **NAT Gateway:** 프라이빗 인스턴스의 아웃바운드 인터넷 접속 지원
- **Security Groups:** Bastion 및 Internal 인스턴스 간 보안 규칙 설정

---

## 📂 테라폼 디렉토리 구조 (Directory Structure)

```text
.
├── main.tf          # VPC, 서브넷, IGW, 라우팅 테이블 등 핵심 인프라 정의
├── security.tf      # Bastion 및 Internal 인스턴스용 보안 그룹(Security Group) 설정
├── compute.tf       # Bastion 및 Internal EC2 인스턴스 정의
├── variables.tf     # 리전, CIDR, 인스턴스 타입 등 변수 관리
├── outputs.tf       # 생성 완료 후 Bastion Public IP 및 내부 IP 출력
├── terraform.png    # 네트워크 아키텍처 다이어그램
└── README.md        # 프로젝트 안내 문서
```

---

## 🔧 테라폼 리소스 설정 (Terraform Resource Configuration)

### main.tf - 핵심 네트워크 인프라
본 파일에서 정의되는 주요 리소스:

| 리소스 | 이름 | 설명 |
| :--- | :--- | :--- |
| `aws_vpc` | `kosta_vpc` | 172.16.0.0/16 CIDR 블록을 가진 VPC 생성 |
| `aws_internet_gateway` | `kosta_igw` | 퍼블릭 서브넷이 외부 인터넷과 통신하기 위한 게이트웨이 |
| `aws_subnet` | `kosta_public_subnet_1/2` | 퍼블릭 서브넷 2개 (ap-northeast-2a/c) |
| `aws_subnet` | `kosta_private_subnet_1/2` | 프라이빗 서브넷 2개 (ap-northeast-2a/c) |
| `aws_route_table` | `kosta_public_rt` | 퍼블릭 서브넷의 라우팅 테이블 (IGW로의 기본 경로) |
| `aws_route_table` | `kosta_private_rt` | 프라이빗 서브넷의 라우팅 테이블 (NAT로의 기본 경로) |
| `aws_nat_gateway` | `kosta_nat_gw` | 프라이빗 인스턴스의 아웃바운드 인터넷 접속 제공 |

### security.tf - 보안 그룹 설정
| 보안 그룹 | 인바운드 규칙 | 아웃바운드 규칙 |
| :--- | :--- | :--- |
| **Bastion-SG** | SSH(22) - 0.0.0.0/0 허용 | 모든 트래픽 허용 |
| **Internal-SG** | SSH(22) - Bastion-SG 허용 | 모든 트래픽 허용 (또는 특정 대상) |

### compute.tf - EC2 인스턴스 및 SSH 키쌍 설정
| 리소스 | 설명 |
| :--- | :--- |
| `tls_private_key` | Terraform이 관리하는 RSA 2048비트 프라이빗 키 생성 |
| `aws_key_pair` | AWS에 공개 키 등록 (키 이름: `kosta`) |
| `local_file` | 프라이빗 키를 로컬 `~/.ssh/kosta.pem`으로 저장 |
| `aws_instance` (Bastion) | kosta-public02 (ap-northeast-2c) - Bastion-SG 적용, kosta 키쌍 사용 |
| `aws_instance` (Internal) | kosta-private01 (ap-northeast-2a) - Internal-SG 적용, kosta 키쌍 사용 |

**키쌍 생성 흐름:**
```
[Terraform] → [tls_private_key] → RSA 프라이빗 키 생성
             ↓
         [aws_key_pair] → AWS에 공개 키 등록 (kosta)
             ↓
         [local_file] → ~/.ssh/kosta.pem 저장 (권한: 0600)
             ↓
         [EC2 인스턴스] → 모든 인스턴스에 kosta 키쌍 자동 적용
```

#### Docker 인스턴스 User Data 설정
**Bastion Host (kosta-public01)에 자동으로 설치될 Docker 환경:**

compute.tf의 user_data 스크립트:
```bash
#!/bin/bash
set -e

# Docker 설치
sudo dnf install -y docker

# Docker 서비스 자동 시작 및 활성화
sudo systemctl enable docker --now

# ec2-user 사용자에게 docker 권한 부여 (sudo 없이 사용 가능)
sudo usermod -aG docker ec2-user
```

**실행 내용 설명:**
| 명령어 | 설명 |
| :--- | :--- |
| `sudo dnf install -y docker` | Amazon Linux 2023의 패키지 관리자 dnf를 사용해 Docker 설치 |
| `sudo systemctl enable docker --now` | Docker 데몬 활성화 및 즉시 시작 |
| `sudo usermod -aG docker ec2-user` | ec2-user 사용자를 docker 그룹에 추가하여 sudo 없이 docker 명령 실행 가능 |

**User Data 적용 방식:**
- EC2 인스턴스 생성 시 자동으로 실행됨
- 인스턴스 생성 후 약 1-2분 후 Docker 설치 완료
- 설치 로그는 `/var/log/cloud-init-output.log`에서 확인 가능

**인스턴스 생성 후 Docker 확인:**
```bash
# Bastion Host 접속
ssh -i ~/.ssh/kosta.pem ec2-user@bastion-public-ip

# Docker 버전 확인
docker version

# Docker 상태 확인
sudo systemctl status docker

# ec2-user로 Docker 명령 실행 가능 확인
docker ps
```

### variables.tf - 환경 변수
```hcl
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
```

### outputs.tf - 출력 값
생성 완료 후 다음 정보가 출력됩니다:
- **Bastion Host Public IP:** 외부에서 SSH 접속 시 사용할 공인 IP
- **Bastion Host Private IP:** 내부 네트워크에서의 접속 주소
- **Internal Instance Private IP:** 내부 인스턴스의 프라이빗 IP
- **VPC ID, Subnet IDs:** 생성된 VPC 및 서브넷 식별자

---

## 🚀 Terraform 사용 방법 (How to Use)

### 0. SSH 키쌍 생성 (사전 필수)
Terraform이 자동으로 AWS 키쌍을 생성하고 프라이빗 키를 로컬에 저장합니다.
```bash
# ~/.ssh 디렉토리 생성 (없는 경우)
mkdir -p ~/.ssh

# terraform apply 실행 시 자동 생성됨
# 생성 후 권한 설정 (자동 적용되지만 명시적으로)
chmod 600 ~/.ssh/kosta.pem
```

**생성 결과:**
- AWS: `kosta` 이름의 KeyPair 생성
- 로컬: `~/.ssh/kosta.pem` 프라이빗 키 저장
- 권한: 0600 (소유자만 읽기)

### 1. 사전 준비
```bash
# AWS CLI 설치 및 인증 정보 설정
aws configure

# 또는 환경 변수로 설정
export AWS_ACCESS_KEY_ID="your-access-key"
export AWS_SECRET_ACCESS_KEY="your-secret-key"
export AWS_DEFAULT_REGION="ap-northeast-2"
```

### 2. Terraform 초기화
```bash
terraform init
```
- `.terraform` 디렉토리 생성
- AWS 플러그인 다운로드
- 상태 파일 초기화

**variables.tf에 추가되는 변수:**
```hcl
variable "key_pair_name" {
  description = "AWS KeyPair 이름"
  default     = "kosta"
}
```

### 3. 계획 검토
```bash
terraform plan
```
- 생성될 리소스 목록 확인
- 설정 오류 사전 검증
- **키쌍 관련 리소스 확인:**
  - `tls_private_key.kosta_key`
  - `aws_key_pair.kosta_keypair`
  - `local_file.kosta_pem`
  - EC2 인스턴스의 `key_name = "kosta"` 설정

### 4. 인프라 구축
```bash
terraform apply
```
- 대화형 모드: 승인 후 리소스 생성
- 자동 승인: `terraform apply -auto-approve`

**실행 결과:**
- AWS KeyPair "kosta" 생성
- 프라이빗 키를 `~/.ssh/kosta.pem`에 저장 (자동 권한 설정)
- Bastion Host와 Internal Instance 모두 "kosta" 키쌍으로 생성

### 5. 리소스 정보 확인
```bash
terraform output
```
- Bastion Host IP, Internal Instance IP 등 확인

### 6. 리소스 삭제
```bash
terraform destroy
```
- 모든 생성된 AWS 리소스 제거

---

## 🔐 보안 고려사항 (Security Best Practices)

### 1. SSH 키 쌍 자동 생성 및 관리

#### 1-1. Terraform에서의 키쌍 생성 방식
compute.tf에서 다음과 같이 구현됩니다:
```hcl
# 1) RSA 프라이빗 키 생성 (Terraform 관리)
resource "tls_private_key" "kosta_key" {
  algorithm = "RSA"
  rsa_bits  = 2048
}

# 2) AWS에 공개 키 등록
resource "aws_key_pair" "kosta_keypair" {
  key_name   = "kosta"
  public_key = tls_private_key.kosta_key.public_key_openssh
}

# 3) 프라이빗 키를 로컬 파일로 저장
resource "local_file" "kosta_pem" {
  filename        = "${pathexpand("~")}/.ssh/kosta.pem"
  content         = tls_private_key.kosta_key.private_key_pem
  file_permission = "0600"
}

# 4) EC2 인스턴스에 키쌍 적용
resource "aws_instance" "bastion" {
  key_name = aws_key_pair.kosta_keypair.key_name
  # ... 기타 설정
}

resource "aws_instance" "internal" {
  key_name = aws_key_pair.kosta_keypair.key_name
  # ... 기타 설정
}
```

#### 1-2. 프라이빗 키 저장 및 권한 설정
- **저장 위치:** `~/.ssh/kosta.pem`
- **권한:** 0600 (소유자만 읽기 가능)
- **자동 생성:** `terraform apply` 실행 시 자동으로 생성되고 저장됨

#### 1-3. 프라이빗 키 보안
```bash
# 프라이빗 키 확인 및 권한 검증
ls -la ~/.ssh/kosta.pem

# 예상 결과: -rw------- (600 권한)

# 추가 보안: 백업
cp ~/.ssh/kosta.pem ~/.ssh/kosta.pem.backup

# 또는 다른 위치에 복사 (USB 등)
cp ~/.ssh/kosta.pem /secure/location/kosta.pem.backup
```

### 2. Bastion Host 접속
```bash
# 자동 생성된 kosta 키쌍을 사용한 접속
ssh -i ~/.ssh/kosta.pem ec2-user@bastion-public-ip

# 또는 키 에이전트 등록
ssh-add ~/.ssh/kosta.pem
ssh ec2-user@bastion-public-ip
```

### 3. Internal Instance 접속 (Bastion 경유)
```bash
# 방법 1: SSH 포트 포워딩 (ProxyJump)
ssh -i ~/.ssh/kosta.pem -J ec2-user@bastion-public-ip ec2-user@internal-private-ip

# 방법 2: SSH 설정 파일 활용
# ~/.ssh/config 파일 추가:
# Host bastion
#     HostName bastion-public-ip
#     User ec2-user
#     IdentityFile ~/.ssh/kosta.pem
#
# Host internal
#     HostName internal-private-ip
#     User ec2-user
#     IdentityFile ~/.ssh/kosta.pem
#     ProxyJump bastion

# 그 후 접속
ssh internal

# 방법 3: Bastion 내에서 직접 접속
ssh -i ~/.ssh/kosta.pem ec2-user@bastion-public-ip
# Bastion 내에서
ssh -i .ssh/kosta.pem ec2-user@internal-private-ip
```

### 4. 키쌍 관련 주의사항

| 항목 | 설명 |
| :--- | :--- |
| **재생성** | `terraform destroy` 후 재구축 시 새로운 키쌍 생성, 기존 pem 파일 덮어쓰기 |
| **손실 방지** | `terraform plan`으로 키쌍 변경 여부 미리 확인 |
| **공유 금지** | `~/.ssh/kosta.pem`을 절대 공개하거나 공유하지 말 것 |
| **버전관리 제외** | .gitignore에 `*.pem` 추가하여 실수로 커밋되는 것 방지 |
| **다중 팀 환경** | 팀 내 공유 필요 시 AWS Secrets Manager, AWS Systems Manager Parameter Store 사용 고려 |

### 5. 보안 그룹 관리
- Bastion 보안 그룹: SSH(22) 포트만 필요시 제한된 IP에서만 허용
- Internal 보안 그룹: Bastion 보안 그룹으로부터만 SSH 허용

---

## 📋 참고사항 (Notes)

- 모든 리소스에 환경 태그(kosta-env, kosta-project 등)가 자동 부여됩니다
- 상태 파일(`terraform.tfstate`)은 버전 관리에서 제외합니다 (.gitignore)
- 프로덕션 환경에서는 S3 백엔드로 상태 파일 관리를 권장합니다
- 비용 최적화: t2.micro는 AWS 프리 티어 대상 인스턴스입니다 (연 1년 무료)