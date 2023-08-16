import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import * as React from "react";
import { Button, Menu, MenuItem, TextField } from "@mui/material";

import AddIcon from "@mui/icons-material/Add";

let WholeWrap = styled.div``;
let TopWrap = styled.div`
  text-align: center;
`;
let TitleWrap = styled.div``;
let ContentWrap = styled.div``;
let BottomWrap = styled.div``;

function UpdateFeed() {
  return (
    <>
      <WholeWrap>
        <TopWrap>
          피드수정<Button variant="contained">수정하기</Button>
        </TopWrap>
        <TitleWrap>
          <TextField
            id="standard-multiline-flexible"
            label="제목"
            multiline
            maxRows={4}
            variant="standard"
          />
        </TitleWrap>
        <ContentWrap>
          <TextField
            id="standard-multiline-static"
            label="피드내용"
            multiline
            rows={4}
            variant="standard"
          />
        </ContentWrap>
        <BottomWrap>
          <div>
            사진/동영상 추가 : <AddIcon></AddIcon>
          </div>
          <div>
            음원 추가 : <AddIcon></AddIcon>
          </div>
        </BottomWrap>
      </WholeWrap>
    </>
  );
}
export default UpdateFeed;
