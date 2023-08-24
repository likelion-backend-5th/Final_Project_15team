import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import React from "react";
import { Button, TextField, Box, Paper } from "@mui/material";

import AddIcon from "@mui/icons-material/Add";

import Appbars from "../components/appbars";

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
      <Appbars></Appbars>
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{
              width: "50%",
              margin: "1.2rem",
              marginRight: "0.4rem",
              textAlign: "center",
            }}>
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
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default UpdateFeed;
