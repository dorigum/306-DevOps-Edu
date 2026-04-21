import './Header.css';

// Header 컴포넌트: 앱의 제목과 오늘 날짜를 표시하는 역할
function Header() {
  // 오늘 날짜를 한국어 형식으로 생성
  const today = new Date().toLocaleDateString('ko-KR', {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });

  return (
    <div className="header">
      <h1 className="header-title">오늘의 Plan 😍</h1>
      <p className="header-date">{today}</p>
    </div>
  );
}

export default Header;