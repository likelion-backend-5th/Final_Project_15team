import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";
import React, { useState, useEffect } from "react";

import Appbars from "../components/appbars.js";
import Searchbox from "../components/searchbox.js";
import Musiccontroller from "../components/musiccontroller.js";
import Feedswrap from "../components/feedswrap.js";

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

let PlayerBox = styled.div`
  width:20rem;
  border-radius: 1rem;
  background: #003a88;
  color: white;
  padding: 0.5rem;
  margin: 1rem;
  box-shadow: 0.2rem 0.2rem 1rem black;
`;

function Mainpage() {
  return (
    <>
      <Appbars></Appbars>
     
      <MainWrap>
        <CenterBox>
          <Searchbox></Searchbox>
          <PlayerBox>
            <Musiccontroller></Musiccontroller>
          </PlayerBox>
        </CenterBox>
        <Feedswrap></Feedswrap>
      </MainWrap>
    </>
  );
}
export default Mainpage;
