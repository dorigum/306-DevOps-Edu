# ☕ 카페 키오스크 프로젝트 명세서 (Cafe Kiosk Project)

## 1. 프로젝트 개요
- **목표**: MVC 패턴을 적용한 콘솔 기반 카페 키오스크 시스템 구축
- **주요 기능**: 회원/비회원 주문 서비스 및 관리자 모드(상품 관리, 매출 통계 등)
- **개발 환경**: Java 18 (OpenJDK), **MySQL 8.0**, JDBC (mysql-connector-j)

## 2. 주요 기능 요구사항

### 👤 사용자(회원/비회원) 기능
- [ ] 회원 가입 및 로그인 (포인트 적립 등)
- [ ] 상품 메뉴 조회 (DB 연동)
- [ ] 상품 선택 및 장바구니 담기
- [ ] 결제 처리 (회원: 포인트/현금, 비회원: 현금)

### 🛠️ 관리자(Admin) 기능
- [x] 상품 등록 및 조회 (**MySQL DB 연동 완료**)
- [ ] 상품 수정 및 삭제 (재고 및 품절 관리)
- [ ] 회원 목록 관리 (조회, 블랙리스트 등)
- [ ] 매출 통계 (기간별 매출액, 인기 상품 분석 등)

## 3. 시스템 구조 (MVC Pattern)
- **Model**: `Product` (MENU 테이블 기반), `User`, `Order` 등 데이터 객체 정의
- **View**: 콘솔 입출력 (AdminController 등에서 처리)
- **Controller**: `AdminController`, `Main` (흐름 제어)
- **Service**: `AdminService` (비즈니스 로직 처리)
- **Repository**: `ProductRepository` (**MySQL JDBC 방식**, `DBUtil` 활용)

## 4. 데이터 설계 (MySQL 연동 완료)
| 구분 | 테이블명 | 주요 필드 | 설명 |
| :--- | :--- | :--- | :--- |
| **Category** | CATEGORY | category_id, category_name | 상품 분류 (Coffee, Dessert 등) |
| **Product** | MENU | menu_id, category_id, menu_name, price, is_available | 상품 정보 (DB 연동) |
| **Member** | MEMBER | member_id, phone, password, point_balance, role | 회원 정보 (설계 완료) |
| **Order** | ORDERS | order_id, member_id, total_amount, status | 주문 내역 (설계 완료) |

## 5. 진행 현황 및 일정
- **[2026-03-11]** 프로젝트 초기 설정 및 MVC 뼈대 구축 완료
- **[2026-03-11]** **MySQL 데이터베이스 연동 완료**
  - `cafeDB.txt` 구조 기반 테이블 설계 (`MENU`, `CATEGORY` 등)
  - `ProductRepository`를 JDBC 통신 방식으로 전환
  - `DBUtil` 클래스 추가 및 관리자 모드 카테고리 입력 기능 구현
- **[계획]** 관리자 세부 기능 구현 (수정/삭제/통계)
- **[계획]** 회원 서비스 및 주문 로직 개발 (DB 연동 기반)
