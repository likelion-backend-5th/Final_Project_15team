import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import React, { useState, useEffect } from "react";
import { Cookies } from "react-cookie";
import axios from "axios";

import Appbars from "../components/appbars.js";
import Searchbox from "../components/searchbox.js";
import Feedswrap from "../components/feedswrap.js";
import Music from "../components/music.js";

let CenterBox = styled.div`
  text-align: center;
`;

let MainWrap = styled.div`
  // max-width: 1300px;
  margin: auto;
  // background: #00457e;
  background: #bbd2ec;
  padding: 1rem;
`;

function Mainpage() {
  const cookies = new Cookies();
  const [JWT, setJWT] = useState();
  //쿠키에 있는 값을 꺼낼때
  const getCookie = (name) => {
    return cookies.get(name);
  };
  useEffect(() => {
    setJWT(getCookie("is_login"));
  }, []);
  const check = () => {
    axios
      .get("http://localhost:8080/users/mypage/" + "aa", {
        headers: JWT,
        data: "",
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <>
      <Appbars></Appbars>
      <MainWrap>
        <button onClick={check}>유저정보요청</button>
        <div style={{ maxWidth: 800 }}>
          <CenterBox>
            <Searchbox></Searchbox>
            <Music />
          </CenterBox>
          <Feedswrap></Feedswrap>
        </div>
      </MainWrap>
    </>
  );
}
export default Mainpage;
