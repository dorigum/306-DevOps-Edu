USE cafe_kiosk;

-- =============================================
-- CATEGORY
-- =============================================
INSERT INTO CATEGORY (category_name) VALUES
  ('커피'),
  ('논커피'),
  ('디저트');

-- =============================================
-- MENU
-- =============================================
INSERT INTO MENU (category_id, menu_name, price, description, is_available, created_at) VALUES
  -- 커피 (category_id = 1)
  (1, '아메리카노',         2000, '깔끔하고 진한 에스프레소에 물을 더한 기본 커피',  1, NOW() - INTERVAL 90 DAY),
  (1, '카페라떼',           3000, '부드러운 우유와 에스프레소의 조화',               1, NOW() - INTERVAL 80 DAY),
  (1, '바닐라라떼',         3500, '달콤한 바닐라 시럽을 더한 라떼',                  1, NOW() - INTERVAL 70 DAY),
  (1, '카푸치노',           3000, '풍성한 우유 거품이 올라간 에스프레소',             1, NOW() - INTERVAL 60 DAY),
  (1, '콜드브루',           3500, '12시간 냉침 추출한 부드러운 커피',                1, NOW() - INTERVAL 10 DAY),
  -- 논커피 (category_id = 2)
  (2, '자몽에이드',         3500, '상큼한 자몽과 탄산의 조화',                       1, NOW() - INTERVAL 50 DAY),
  (2, '레몬에이드',         3500, '새콤달콤한 레몬에이드',                            1, NOW() - INTERVAL 40 DAY),
  (2, '딸기스무디',         4000, '달콤한 딸기로 만든 스무디',                        1, NOW() - INTERVAL 30 DAY),
  (2, '그린티라떼',         3500, '향긋한 말차와 우유의 조화',                        1, NOW() - INTERVAL 20 DAY),
  (2, '초코라떼',           3500, '진한 초콜릿과 우유의 달콤한 조합',                 1, NOW() - INTERVAL 7 DAY),
  -- 디저트 (category_id = 3)
  (3, '치즈케이크',         4500, '부드럽고 진한 뉴욕식 치즈케이크',                  1, NOW() - INTERVAL 60 DAY),
  (3, '초코쿠키',           2000, '바삭하고 달콤한 초콜릿 쿠키',                      1, NOW() - INTERVAL 50 DAY),
  (3, '크로플',             4000, '겉바속촉 크로플에 생크림을 곁들인 디저트',          1, NOW() - INTERVAL 5 DAY),
  (3, '마들렌',             2500, '촉촉하고 부드러운 버터 마들렌',                    1, NOW() - INTERVAL 4 DAY),
  (3, '소금빵',             2500, '겉은 바삭 속은 촉촉한 소금빵',                     0, NOW() - INTERVAL 30 DAY);  -- 품절

-- =============================================
-- OPTION_GROUP
-- =============================================
INSERT INTO OPTION_GROUP (group_name) VALUES
  ('사이즈'),    -- group_id = 1
  ('온도'),      -- group_id = 2
  ('카페인'),    -- group_id = 3
  ('휘핑');      -- group_id = 4

-- =============================================
-- OPTION
-- =============================================
INSERT INTO `OPTION` (group_id, option_name, extra_price) VALUES
  -- 사이즈
  (1, 'Regular', 0),
  (1, 'Large',   500),
  -- 온도
  (2, 'HOT',     0),
  (2, 'COLD',    0),
  -- 카페인
  (3, '카페인',   0),
  (3, '디카페인', 500),
  -- 휘핑
  (4, '휘핑 없음', 0),
  (4, '휘핑 추가', 300);

-- =============================================
-- MENU_OPTION_GROUP
-- 커피: 사이즈/온도/카페인/휘핑
-- 논커피: 사이즈/온도
-- 디저트: 옵션 없음
-- =============================================
INSERT INTO MENU_OPTION_GROUP (menu_id, group_id) VALUES
  -- 아메리카노 (1)
  (1, 1), (1, 2), (1, 3),
  -- 카페라떼 (2)
  (2, 1), (2, 2), (2, 3), (2, 4),
  -- 바닐라라떼 (3)
  (3, 1), (3, 2), (3, 3), (3, 4),
  -- 카푸치노 (4)
  (4, 1), (4, 2), (4, 3), (4, 4),
  -- 콜드브루 (5) — COLD 전용이라 온도 옵션 없음
  (5, 1), (5, 3),
  -- 자몽에이드 (6)
  (6, 1), (6, 2),
  -- 레몬에이드 (7)
  (7, 1), (7, 2),
  -- 딸기스무디 (8)
  (8, 1),
  -- 그린티라떼 (9)
  (9, 1), (9, 2), (9, 4),
  -- 초코라떼 (10)
  (10, 1), (10, 2), (10, 4);

-- =============================================
-- MEMBER (관리자 1명 + 회원 9명)
-- =============================================
INSERT INTO MEMBER (phone, password, age, point_balance, role, created_at) VALUES
  ('010-0000-0000', 'hashed_admin_pw',  35, 0,    'ADMIN', NOW() - INTERVAL 200 DAY),
  ('010-1111-1111', 'hashed_pw_user1',  25, 1500,  'USER', NOW() - INTERVAL 150 DAY),
  ('010-2222-2222', 'hashed_pw_user2',  28, 3200,  'USER', NOW() - INTERVAL 120 DAY),
  ('010-3333-3333', 'hashed_pw_user3',  32, 800,   'USER', NOW() - INTERVAL 100 DAY),
  ('010-4444-4444', 'hashed_pw_user4',  22, 0,     'USER', NOW() - INTERVAL 90 DAY),
  ('010-5555-5555', 'hashed_pw_user5',  29, 5000,  'USER', NOW() - INTERVAL 80 DAY),
  ('010-6666-6666', 'hashed_pw_user6',  34, 2100,  'USER', NOW() - INTERVAL 60 DAY),
  ('010-7777-7777', 'hashed_pw_user7',  27, 400,   'USER', NOW() - INTERVAL 45 DAY),
  ('010-8888-8888', 'hashed_pw_user8',  31, 900,   'USER', NOW() - INTERVAL 30 DAY),
  ('010-9999-9999', 'hashed_pw_user9',  24, 0,     'USER', NOW() - INTERVAL 10 DAY);

-- =============================================
-- WISHLIST
-- =============================================
INSERT INTO WISHLIST (member_id, menu_id, created_at) VALUES
  (2, 1, NOW() - INTERVAL 100 DAY),
  (2, 5, NOW() - INTERVAL 50 DAY),
  (3, 2, NOW() - INTERVAL 80 DAY),
  (3, 11, NOW() - INTERVAL 40 DAY),
  (4, 1, NOW() - INTERVAL 60 DAY),
  (4, 8, NOW() - INTERVAL 20 DAY),
  (5, 3, NOW() - INTERVAL 70 DAY),
  (6, 10, NOW() - INTERVAL 30 DAY),
  (7, 1, NOW() - INTERVAL 25 DAY),
  (8, 13, NOW() - INTERVAL 3 DAY);

-- =============================================
-- ORDERS + ORDER_ITEM + ORDER_ITEM_OPTION
-- 프로시저로 30건 생성
-- =============================================
DELIMITER $$

CREATE PROCEDURE insert_sample_orders()
BEGIN
  DECLARE i       INT DEFAULT 1;
  DECLARE v_order_id    BIGINT;
  DECLARE v_member_id   BIGINT;
  DECLARE v_menu_id     BIGINT;
  DECLARE v_quantity    INT;
  DECLARE v_unit_price  INT;
  DECLARE v_total       INT;
  DECLARE v_point_used  INT;
  DECLARE v_point_earned INT;
  DECLARE v_status      VARCHAR(10);
  DECLARE v_date        DATETIME;
  DECLARE v_item_id     BIGINT;

  WHILE i <= 30 DO

    -- 랜덤 회원 (비회원 포함: member_id 2~10, NULL)
    SET v_member_id = CASE
      WHEN i % 5 = 0 THEN NULL
      ELSE (MOD(i, 9) + 2)
    END;

    -- 랜덤 날짜 (최근 90일 내)
    SET v_date = NOW() - INTERVAL FLOOR(RAND() * 90) DAY;

    -- 주문 상태
    SET v_status = CASE
      WHEN i % 7 = 0 THEN 'CANCELLED'
      WHEN i % 10 = 0 THEN 'PENDING'
      ELSE 'COMPLETED'
    END;

    SET v_point_used   = CASE WHEN v_member_id IS NOT NULL AND i % 3 = 0 THEN 1000 ELSE 0 END;
    SET v_total        = 0;

    -- 주문 insert (total_amount는 나중에 UPDATE)
    INSERT INTO ORDERS (member_id, total_amount, point_used, point_earned, status, order_date)
    VALUES (v_member_id, 0, v_point_used, 0, v_status, v_date);

    SET v_order_id = LAST_INSERT_ID();

    -- 주문 항목 1~3개 랜덤 생성
    BEGIN
      DECLARE j INT DEFAULT 1;
      DECLARE item_count INT;
      SET item_count = FLOOR(RAND() * 3) + 1;

      WHILE j <= item_count DO
        SET v_menu_id    = FLOOR(RAND() * 14) + 1;  -- menu_id 1~14 (품절 15 제외)
        SET v_quantity   = FLOOR(RAND() * 3) + 1;
        SELECT price INTO v_unit_price FROM MENU WHERE menu_id = v_menu_id;

        INSERT INTO ORDER_ITEM (order_id, menu_id, quantity, unit_price)
        VALUES (v_order_id, v_menu_id, v_quantity, v_unit_price);

        SET v_item_id = LAST_INSERT_ID();
        SET v_total   = v_total + (v_unit_price * v_quantity);

        -- 옵션 스냅샷: 메뉴에 연결된 옵션 그룹마다 랜덤 옵션 1개씩
        INSERT INTO ORDER_ITEM_OPTION (order_item_id, option_id)
        SELECT v_item_id, opt.option_id
        FROM MENU_OPTION_GROUP mog
        JOIN (
          SELECT o.option_id, o.group_id,
                 ROW_NUMBER() OVER (PARTITION BY o.group_id ORDER BY RAND()) AS rn
          FROM `OPTION` o
        ) opt ON opt.group_id = mog.group_id AND opt.rn = 1
        WHERE mog.menu_id = v_menu_id;

        SET j = j + 1;
      END WHILE;
    END;

    -- total_amount / point_earned 업데이트
    SET v_total        = v_total - v_point_used;
    SET v_point_earned = CASE WHEN v_status = 'COMPLETED' THEN FLOOR(v_total * 0.1) ELSE 0 END;

    UPDATE ORDERS
    SET total_amount = v_total,
        point_earned = v_point_earned
    WHERE order_id = v_order_id;

    -- 회원 포인트 잔액 반영
    IF v_member_id IS NOT NULL AND v_status = 'COMPLETED' THEN
      UPDATE MEMBER
      SET point_balance = point_balance + v_point_earned - v_point_used
      WHERE member_id = v_member_id;
    END IF;

    SET i = i + 1;
  END WHILE;
END$$

DELIMITER ;

CALL insert_sample_orders();
DROP PROCEDURE insert_sample_orders;
/*
```

---

### 실행 순서
```
1. DDL 전체 실행 (테이블 생성)
2. 샘플 데이터 순서대로 실행
   CATEGORY → MENU → OPTION_GROUP → OPTION
   → MENU_OPTION_GROUP → MEMBER → WISHLIST
   → 프로시저 (ORDERS + ORDER_ITEM + ORDER_ITEM_OPTION 자동 생성)
*/