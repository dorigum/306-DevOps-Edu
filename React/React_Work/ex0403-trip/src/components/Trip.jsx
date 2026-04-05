import Hawaii from "../assets/images/Hawaii.jpg";

function Trip() {
  return (
    <div className="imgContainer">
      <img src={Hawaii} className="imgStyle" alt="여행 사진" />
    </div>
  );
}

export default Trip;
