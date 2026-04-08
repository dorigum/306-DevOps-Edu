import React from "react";
import { useSearchParams } from "react-router-dom";

const Admin = () => {
  // 요청할 때 querystring 형태 ? name=value&name=value
  const [params, setParams] = useSearchParams();

  console.log(params);
  console.log(params.get("name"));

  return (
    <div>
      <h1>Admin 페이지입니다.</h1>
      <h1>
        {params.get("name")} / {params.get("age")}
      </h1>
    </div>
  );
};

export default Admin;
