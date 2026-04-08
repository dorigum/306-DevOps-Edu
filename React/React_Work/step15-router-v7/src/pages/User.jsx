import React from "react";
import { useParams } from "react-router-dom";

function User() {
    const params = useParams();
    console.log(params);

  return (
    <div>
      <h1>User 영역입니다. - {params.id}</h1>
    </div>
  );
}

export default User;
