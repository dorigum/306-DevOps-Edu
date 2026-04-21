import { test, expect } from '@playwright/test';

const BASE_URL = 'http://localhost:5175';

test.describe('TodoList 초기 화면 검증', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto(BASE_URL);
  });

  test('페이지가 정상적으로 열린다', async ({ page }) => {
    await expect(page).toHaveTitle(/todo/i);
    await expect(page.locator('.app')).toBeVisible();
  });

  test('초기 Todo 항목 3개가 표시된다', async ({ page }) => {
    const items = page.locator('.todo-item');
    await expect(items).toHaveCount(3);
  });

  test('초기 Todo 항목 텍스트가 올바르게 표시된다', async ({ page }) => {
    await expect(page.locator('.todo-content').nth(0)).toHaveText('리액트 공부하기');
    await expect(page.locator('.todo-content').nth(1)).toHaveText('할 일 목록 프로젝트 완성하기');
    await expect(page.locator('.todo-content').nth(2)).toHaveText('코드 리뷰하기');
  });

  test('완료된 항목에 done 클래스가 적용된다', async ({ page }) => {
    // 세 번째 항목(코드 리뷰하기)은 isDone: true
    const doneItem = page.locator('.todo-content.done');
    await expect(doneItem).toHaveCount(1);
    await expect(doneItem).toHaveText('코드 리뷰하기');
  });

  test('입력창과 추가 버튼이 표시된다', async ({ page }) => {
    await expect(page.locator('.editor-input')).toBeVisible();
    await expect(page.locator('.editor-btn')).toBeVisible();
  });

  test('검색창이 표시된다', async ({ page }) => {
    await expect(page.locator('.list-search')).toBeVisible();
  });

  test('통계 정보가 올바르게 표시된다 (완료 1 / 전체 3)', async ({ page }) => {
    await expect(page.locator('.list-stats')).toContainText('완료: 1 / 전체: 3');
  });

  test('각 항목에 체크박스와 삭제 버튼이 있다', async ({ page }) => {
    const items = page.locator('.todo-item');
    const count = await items.count();
    for (let i = 0; i < count; i++) {
      await expect(items.nth(i).locator('.todo-checkbox')).toBeVisible();
      await expect(items.nth(i).locator('.todo-delete-btn')).toBeVisible();
    }
  });
});
