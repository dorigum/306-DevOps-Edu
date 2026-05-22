USE cafe_kiosk;


-- 메뉴별
SELECT 
    m.menu_name,
    SUM(oi.quantity) AS total_qty,
    SUM(oi.quantity * oi.unit_price) AS total_sales
FROM ORDER_ITEM oi
JOIN MENU m ON oi.menu_id = m.menu_id
JOIN ORDERS o ON oi.order_id = o.order_id
WHERE o.status = 'COMPLETED'
GROUP BY m.menu_id, m.menu_name
ORDER BY total_sales DESC;

-- 카테고리별
SELECT 
    c.category_name,
    SUM(oi.quantity) AS total_qty,
    SUM(oi.quantity * oi.unit_price) AS total_sales
FROM ORDER_ITEM oi
JOIN MENU m ON oi.menu_id = m.menu_id
JOIN CATEGORY c ON m.category_id = c.category_id
JOIN ORDERS o ON oi.order_id = o.order_id
WHERE o.status = 'COMPLETED'
GROUP BY c.category_id, c.category_name
ORDER BY total_sales DESC;

-- 영수증
SELECT 
    o.order_id,
    o.order_date,
    m.menu_name,
    oi.quantity,
    oi.unit_price,
    og.group_name   AS option_group,
    opt.option_name AS option_value,
    opt.extra_price
FROM ORDERS o
JOIN ORDER_ITEM oi        ON o.order_id        = oi.order_id
JOIN MENU m               ON oi.menu_id         = m.menu_id
LEFT JOIN ORDER_ITEM_OPTION oio ON oi.order_item_id = oio.order_item_id
LEFT JOIN `OPTION` opt      ON oio.option_id      = opt.option_id
LEFT JOIN OPTION_GROUP og ON opt.group_id       = og.group_id
WHERE o.order_id = 1
ORDER BY oi.order_item_id;

select * from `option`;



-- =============================================
-- 주문 흐름 예시
-- 회원(member_id=2)이 아메리카노(menu_id=1) Large/COLD/디카페인으로 1잔 주문
-- =============================================

-- Step 1. 주문 생성 (PENDING)
INSERT INTO ORDERS (member_id, total_amount, point_used, point_earned, status)
VALUES (2, 0, 0, 0, 'PENDING');

SET @order_id = LAST_INSERT_ID();

-- Step 2. 주문 항목 등록
INSERT INTO ORDER_ITEM (order_id, menu_id, quantity, unit_price)
VALUES (@order_id, 1, 1, 2000);   -- 아메리카노 기본가 2000

SET @order_item_id = LAST_INSERT_ID();

-- Step 3. 옵션 선택 등록
-- Large(option_id=2, +500), COLD(option_id=4, +0), 디카페인(option_id=6, +500)
INSERT INTO ORDER_ITEM_OPTION (order_item_id, option_id)
VALUES
  (@order_item_id, 2),   -- Large     +500
  (@order_item_id, 4),   -- COLD      +0
  (@order_item_id, 6);   -- 디카페인  +500

-- Step 4. 총 금액 계산 후 업데이트
--   unit_price + 선택한 옵션 extra_price 합산
UPDATE ORDERS
SET total_amount = (
    SELECT SUM(oi.unit_price * oi.quantity) + SUM(o.extra_price * oi.quantity)
    FROM ORDER_ITEM oi
    JOIN ORDER_ITEM_OPTION oio ON oi.order_item_id = oio.order_item_id
    JOIN `OPTION` oio2 ON oio.option_id = oio2.option_id  -- MENU_OPTION = 이름 바꾼 OPTION 테이블
    JOIN `OPTION` o ON oio.option_id = o.option_id
    WHERE oi.order_id = @order_id
)
WHERE order_id = @order_id;

-- 위 쿼리를 단순하게 쓰면
UPDATE ORDERS
SET total_amount = (
    SELECT SUM((oi.unit_price + opt_sum.extra_total) * oi.quantity)
    FROM ORDER_ITEM oi
    JOIN (
        SELECT oio.order_item_id, SUM(o.extra_price) AS extra_total
        FROM ORDER_ITEM_OPTION oio
        JOIN `OPTION` o ON oio.option_id = o.option_id
        GROUP BY oio.order_item_id
    ) opt_sum ON oi.order_item_id = opt_sum.order_item_id
    WHERE oi.order_id = @order_id
)
WHERE order_id = @order_id;

-- Step 5. 포인트 사용 (선택, 회원만)
--   1000포인트 사용 시
UPDATE ORDERS
SET point_used   = 1000,
    total_amount = total_amount - 1000
WHERE order_id = @order_id;

UPDATE MEMBER
SET point_balance = point_balance - 1000
WHERE member_id = 2;

-- Step 6. 결제 완료 — PENDING → COMPLETED
START TRANSACTION;

  UPDATE ORDERS
  SET status       = 'COMPLETED',
      point_earned = FLOOR(total_amount * 0.1)  -- 결제 금액의 10% 적립
  WHERE order_id = @order_id;

  -- 회원 포인트 적립
  UPDATE MEMBER
  SET point_balance = point_balance + (
      SELECT point_earned FROM ORDERS WHERE order_id = @order_id
  )
  WHERE member_id = 2;

COMMIT;

-- =============================================
-- 결과 확인
-- =============================================
SELECT
    o.order_id,
    o.status,
    o.total_amount,
    o.point_used,
    o.point_earned,
    m.menu_name,
    oi.quantity,
    oi.unit_price,
    og.group_name,
    opt.option_name,
    opt.extra_price
FROM ORDERS o
JOIN ORDER_ITEM oi          ON o.order_id         = oi.order_id
JOIN MENU m                 ON oi.menu_id          = m.menu_id
JOIN ORDER_ITEM_OPTION oio