import React from "react";
import { Outlet } from "react-router-dom";

function AdminLayout() {
  return (
    <div style={{ backgroundColor: "lightblue" }}>
      <h3>관리자 공통 영역입니다:D</h3>
      <Outlet />
    </div>
  );
}

export default AdminLayout;
