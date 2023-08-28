import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";

import React, { useState } from "react";
import { Button, TextField, Box, Paper } from "@mui/material";

import AddIcon from "@mui/icons-material/Add";

import Appbars from "../components/appbars";

let WholeWrap = styled.div``;
let TopWrap = styled.div`
  text-align: center;
  padding: 0.8rem;
  font-size: 1.2rem;
`;
let TitleWrap = styled.div``;
let ContentWrap = styled.div``;
let BottomWrap = styled.div``;

function CreateFeed() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const OCTitle = (e) => {
    setTitle(e.target.value);
  };
  const OCContent = (e) => {
    setContent(e.target.value);
  };

  const postFeed = (e) => {
    e.preventDefault();
    let body = {
      title: title,
      content: content,
    };
    axios
      .post("http://localhost:8080/feed/add", body)
      .then((res) => {
        console.log(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
      <Appbars></Appbars>
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{
              width: "50rem",
              margin: "1.2rem",
              padding: "0.8rem",
            }}
          >
            <TopWrap>
              피드생성
              <Button variant="contained" onClick={postFeed}>
                게시하기
              </Button>
            </TopWrap>
            <Paper elevation={3} style={{ textAlign: "center" }}>
              <TitleWrap>
                <TextField
                  id="standard-multiline-flexible"
                  label="제목"
                  multiline
                  maxRows={4}
                  variant="standard"
                  value={title}
                  onChange={OCTitle}
                />
              </TitleWrap>
              <ContentWrap>
                <TextField
                  id="standard-multiline-static"
                  label="피드내용"
                  multiline
                  rows={4}
                  variant="standard"
                  value={content}
                  onChange={OCContent}
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
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default CreateFeed;
