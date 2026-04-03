export let num = 0;
export default function Ex01_Export() {
  // 기능
  return (
    <>
      <h3>여기는 Ex01_Export 입니다.</h3>
    </>
  );
}

export /* default */ function Ex01_Export2() { // default는 하나만 사용 가능!!!!
  return (
    <>
      <h3>여기는 Ex01_Export2 입니다.</h3>
    </>
  );
}
