import React, { memo } from "react";
import "./TodoItem.css";

const TodoItem = ({ id, isDone, content, date, onUpdate, onDelete }) => {
  console.log("TodoItem call");

  const onChangeCheckbox = () => {
    // 수정하기(checkbox 상태 변경)
    onUpdate(id);
  };

  return (
    <div className="TodoItem">
      <input type="checkbox" checked={isDone} onChange={onChangeCheckbox} />
      <div className="content">{content}</div>
      <div className="date">{new Date(date).toLocaleString()}</div>
      <button onClick={onClickDeleteButton}>삭제</button>
    </div>
  );
};

// React.memo() 적용
// ⚠props가 불변 객체여야 최적화 효과가 적용
// ⚠직접 비교하는 커스텀마이징 필요
// export default memo(TodoItem, (prevProps, nextProps) => {
//   // 리턴 값에 Props가 바뀌었는지 판단
//   // true: Props 변경 X -> 리렌더링 X
//   // false: Props 변경 O -> 리렌더링 O

//   if (prevProps.id !== nextProps.id) return false;
//   if (prevProps.isDone !== nextProps.isDone) return false;
//   if (prevProps.content !== nextProps.content) return false;
//   if (prevProps.date !== nextProps.date) return false;

//   return true; // 리렌더링 X
// });

// ⛔커스텀마이징을 하면 속성이 변경되거나 추가될 때마다 매번 소스를 수정해야 함 -> 유지보수 어려움!!!!
// ✅useCallback() 활용해서 개선
export default memo(TodoItem);
