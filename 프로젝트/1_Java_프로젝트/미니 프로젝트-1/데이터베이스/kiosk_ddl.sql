-- =============================================
-- 카페 키오스크 서비스 DDL
-- =============================================

CREATE DATABASE IF NOT EXISTS cafe_kiosk
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE cafe_kiosk;

-- 회원
CREATE TABLE MEMBER (
  member_id     BIGINT       NOT NULL AUTO_INCREMENT,
  phone         VARCHAR(20),
  password      VARCHAR(255) NOT NULL,
  age           INT,
  point_balance INT          NOT NULL DEFAULT 0,
  role          VARCHAR(10)  NOT NULL DEFAULT 'USER' COMMENT 'USER / ADMIN',
  created_at    DATETIME     NOT NULL DEFAULT NOW(),
  PRIMARY KEY (member_id)
);

-- 카테고리
CREATE TABLE CATEGORY (
  category_id   INT         NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (category_id)
);

-- 메뉴
CREATE TABLE MENU (
  menu_id      BIGINT       NOT NULL AUTO_INCREMENT,
  category_id  INT          NOT NULL,
  menu_name    VARCHAR(100) NOT NULL,
  price        INT          NOT NULL,
  description  TEXT,
  is_available TINYINT(1)   NOT NULL DEFAULT 1 COMMENT '0: 품절, 1: 판매중',
  created_at   DATETIME     NOT NULL DEFAULT NOW(),
  PRIMARY KEY (menu_id),
  FOREIGN KEY (category_id) REFERENCES CATEGORY (category_id),
  INDEX idx_menu_category_id (category_id),
  INDEX idx_menu_is_available (is_available),
  INDEX idx_menu_created_at (created_at)
);

-- 옵션 그룹
CREATE TABLE OPTION_GROUP (
  group_id   INT         NOT NULL AUTO_INCREMENT,
  group_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (group_id)
);

-- 옵션
CREATE TABLE `OPTION` (
  option_id   INT         NOT NULL AUTO_INCREMENT,
  group_id    INT         NOT NULL,
  option_name VARCHAR(30) NOT NULL,
  extra_price INT         NOT NULL DEFAULT 0,
  PRIMARY KEY (option_id),
  FOREIGN KEY (group_id) REFERENCES OPTION_GROUP (group_id),
  INDEX idx_option_group_id (group_id)
);

-- 메뉴-옵션그룹 매핑
CREATE TABLE MENU_OPTION_GROUP (
  menu_id  BIGINT NOT NULL,
  group_id INT    NOT NULL,
  PRIMARY KEY (menu_id, group_id),
  FOREIGN KEY (menu_id)  REFERENCES MENU (menu_id),
  FOREIGN KEY (group_id) REFERENCES OPTION_GROUP (group_id)
);

-- 관심상품
CREATE TABLE WISHLIST (
  wishlist_id BIGINT   NOT NULL AUTO_INCREMENT,
  member_id   BIGINT   NOT NULL,
  menu_id     BIGINT   NOT NULL,
  created_at  DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (wishlist_id),
  FOREIGN KEY (member_id) REFERENCES MEMBER (member_id),
  FOREIGN KEY (menu_id)   REFERENCES MENU (menu_id)
);

-- 주문
CREATE TABLE ORDERS (
  order_id     BIGINT      NOT NULL AUTO_INCREMENT,
  member_id    BIGINT,
  total_amount INT         NOT NULL,
  point_used   INT         NOT NULL DEFAULT 0,
  point_earned INT         NOT NULL DEFAULT 0,
  status       VARCHAR(10) NOT NULL DEFAULT 'PENDING' COMMENT 'PENDING / COMPLETED / CANCELLED',
  order_date   DATETIME    NOT NULL DEFAULT NOW(),
  PRIMARY KEY (order_id),
  FOREIGN KEY (member_id) REFERENCES MEMBER (member_id),
  INDEX idx_orders_status_date   (status, order_date),
  INDEX idx_orders_member_status (member_id, status)
);

-- 주문 항목
CREATE TABLE ORDER_ITEM (
  order_item_id BIGINT NOT NULL AUTO_INCREMENT,
  order_id      BIGINT NOT NULL,
  menu_id       BIGINT NOT NULL,
  quantity      INT    NOT NULL,
  unit_price    INT    NOT NULL,
  PRIMARY KEY (order_item_id),
  FOREIGN KEY (order_id) REFERENCES ORDERS (order_id),
  FOREIGN KEY (menu_id)  REFERENCES MENU (menu_id),
  INDEX idx_order_item_order_id (order_id),
  INDEX idx_order_item_menu_id  (menu_id)
);

-- 주문 옵션 스냅샷
CREATE TABLE ORDER_ITEM_OPTION (
  order_item_id BIGINT NOT NULL,
  option_id     INT    NOT NULL,
  PRIMARY KEY (order_item_id, option_id),
  FOREIGN KEY (order_item_id) REFERENCES ORDER_ITEM (order_item_id),
  FOREIGN KEY (option_id)     REFERENCES `OPTION` (option_id)
);