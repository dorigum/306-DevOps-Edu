import { test, expect } from '@playwright/test';

test('analyze todo app', async ({ page }) => {
  await page.goto('http://localhost:5173');
  
  console.log('--- Screen Structure Analysis ---');
  
  // 1. 새로운 todo 입력창
  const editorInput = page.locator('.editor-input');
  const hasInput = await editorInput.isVisible();
  console.log(`1. New Todo Input: ${hasInput ? 'Present' : 'Missing'} (Selector: .editor-input)`);
  
  // 2. 추가 버튼
  const addBtn = page.locator('.editor-btn');
  const hasAddBtn = await addBtn.isVisible();
  console.log(`2. Add Button: ${hasAddBtn ? 'Present' : 'Missing'} (Selector: .editor-btn)`);
  
  // 3. 검색 input
  const searchInput = page.locator('.list-search');
  const hasSearchInput = await searchInput.isVisible();
  console.log(`3. Search Input: ${hasSearchInput ? 'Present' : 'Missing'} (Selector: .list-search)`);
  
  // 4. Todo 목록 항목
  const todoItems = page.locator('.todo-item');
  const count = await todoItems.count();
  console.log(`4. Todo Items: ${count} items found (Selector: .todo-item)`);
  
  if (count > 0) {
    // 5. 체크박스
    const checkbox = todoItems.first().locator('.todo-checkbox');
    console.log(`5. Checkbox: ${await checkbox.isVisible() ? 'Present' : 'Missing'} (Selector: .todo-checkbox)`);
    
    // 6. 삭제 버튼
    const deleteBtn = todoItems.first().locator('.todo-delete-btn');
    console.log(`6. Delete Button: ${await deleteBtn.isVisible() ? 'Present' : 'Missing'} (Selector: .todo-delete-btn)`);
  }
});
