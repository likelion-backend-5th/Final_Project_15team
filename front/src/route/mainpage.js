import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import React from "react";

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
  return (
    <>
      <Appbars></Appbars>
      <MainWrap>
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
