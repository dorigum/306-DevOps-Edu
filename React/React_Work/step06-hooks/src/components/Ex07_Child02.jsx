import Ex07_Child03 from "./Ex07_Child03";

const Ex07_Child02 = () => {
  return (
    <div style={{ border: "1px green solid", padding: "10px" }}>
      <h3>Child02입니다.</h3>
      <Ex07_Child03 />
    </div>
  );
};

export default Ex07_Child02;
