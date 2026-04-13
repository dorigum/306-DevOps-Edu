import "./App.css";
import axiosInstance from "./api/axiosInstance";

function UserCRUD() {
  // ---------------------------------------------------------------------
  // 등록, 삭제, 수정, 부분 조회, 전체 조회
  // 1. 등록
  const insertUser = async () => {
    try {
      const result = await axiosInstance.post("/users", {
        name: "이가현",
        email: "lee@daum.net",
        age: 20,
      });
      console.log(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  // 2. 삭제
  const deleteUser = async () => {
    //   axiosInstance({
    //     method: "DELETE",
    //     url: "/users/3",
    //   })
    //     .then((result) => {
    //       console.log(result);
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // };
    try {
      const result = await axiosInstance.delete("/users/1");
      console.log(result.data);
    } catch (err) {
      console.log(err);
    }
  };
  // 3. 수정
  const updateUser = async () => {
    // axiosInstance({
    //   method: "PUT",
    //   url: "/users/2",
    //   data: {
    //     id: "2",
    //     name: "도연2",
    //     email: "koo3@google.com",
    //     age: 30,
    //   },
    // })
    //   .then((result) => {
    //     console.log(result);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
    try {
      const result = await axiosInstance.patch("/users/1", {
        name: "도연3",
        email: "koo3@google.com",
      });
      console.log(result.data);
    } catch (err) {
      console.log(err);
    }
  };
  // 4. 부분 조회
  const getbyId = async () => {
    // axiosInstance({
    //   method: "GET",
    //   url: "/users/2",
    // })
    //   .then((result) => {
    //     console.log(result);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
    try {
      const result = await axiosInstance.get("/users/1");
      console.log(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  // 5. 전체 조회
  const getUsers = async () => {
    // axiosInstance({
    //   method: "GET",
    //   url: "/users",
    // })
    //   .then((result) => {
    //     console.log(result);
    //   })
    //   .catch((err) => {
    //     console.log(err);
    //   });
    try {
      const result = await axiosInstance.get("/users");
      console.log(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
      {/* <h1>Axios Test</h1>
      <h1>fetch Test</h1> */}
      {/* <button onClick={axiosSelectAll}>get - axiosSelectAll</button> */}
      {/* <button onClick={axiosSelectById}>get - axiosSelectById</button> */}

      <h3>json-server 연동하기(CRUD)</h3>
      <button onClick={insertUser}>post - user 등록</button>
      <button onClick={deleteUser}>delete - user 삭제</button>
      <button onClick={updateUser}>put - user 수정</button>
      <button onClick={getbyId}>get - user 부분 조회 </button>
      <button onClick={getUsers}>get - user 전체 조회</button>
    </>
  );
}

export default UserCRUD;
