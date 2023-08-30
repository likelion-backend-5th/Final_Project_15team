import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";

import React, { useState, useRef } from "react";
import { Button, TextField, Box, Paper, IconButton } from "@mui/material";

import AddIcon from "@mui/icons-material/Add";

import Appbars from "../components/appbars";

import SendIcon from "@mui/icons-material/Send";

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
  const [PnV, setPnV] = useState("");
  const PnVRef = useRef();
  const [musicFile, setMusicFile] = useState("");
  const musicRef = useRef();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [viewUploadPnV, setViewUploadPnV] = useState(false);
  const [viewUploadMusic, setViewUploadMusic] = useState(false);

  const uploadPnV = () => {
    const file = PnVRef.current.file[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      setPnV(reader.result);
    };
  };

  const uploadMusic = () => {
    const file = musicRef.current.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      setMusicFile(reader.result);
    };
  };

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
      music: musicFile,
      PnV: PnV,
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
            <TopWrap>피드생성</TopWrap>
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
                  사진/동영상 추가 :{" "}
                  <IconButton>
                    <AddIcon
                      onClick={() => {
                        if (viewUploadPnV) {
                          setViewUploadPnV(false);
                        } else {
                          setViewUploadPnV(true);
                        }
                      }}
                    />
                  </IconButton>
                  {viewUploadPnV ? (
                    <form>
                      <input
                        type="file"
                        onChange={uploadPnV}
                        ref={PnVRef}
                      ></input>
                      <IconButton>
                        <SendIcon />
                      </IconButton>
                    </form>
                  ) : null}
                </div>
                <div>
                  음원 추가 :{" "}
                  <IconButton>
                    <AddIcon
                      onClick={() => {
                        if (viewUploadMusic) {
                          setViewUploadMusic(false);
                        } else {
                          setViewUploadMusic(true);
                        }
                      }}
                    />
                  </IconButton>
                  {viewUploadMusic ? (
                    <form>
                      <input
                        type="file"
                        onChange={uploadMusic}
                        ref={musicRef}
                      ></input>
                      <IconButton>
                        <SendIcon />
                      </IconButton>
                    </form>
                  ) : null}
                </div>
                <Button variant="contained" onClick={postFeed}>
                  게시하기
                </Button>
              </BottomWrap>
            </Paper>
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default CreateFeed;
