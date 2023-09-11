import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";

import { useNavigate } from "react-router-dom";
import React, { useState, useRef } from "react";
import { Button, TextField, Box, Paper, IconButton } from "@mui/material";

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

function CreateFeed(props) {
  const navigate = useNavigate();
  const [PnV, setPnV] = useState("");
  const PnVRef = useRef();
  const [musicFile, setMusicFile] = useState("");
  const musicRef = useRef();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [hashtag, setHashtag] = useState("");
  const [viewUploadPnV, setViewUploadPnV] = useState(false);
  const [viewUploadMusic, setViewUploadMusic] = useState(false);

  const uploadPnV = () => {
    const file = PnVRef.current.files[0];
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
  const OCHashtag = (e) => {
    setHashtag(e.target.value);
  };

  const postFeed = (e) => {
    e.preventDefault();
    const formData = new FormData();

    const changedPnV = new Blob([PnV], { type: "multipart/form-data" });
    formData.append("file", changedPnV);
    const changedMusicFile = new Blob([musicFile], {
      type: "multipart/form-data",
    });
    formData.append("musicFile", changedMusicFile);

    const dto = JSON.stringify({ title, content });
    const changedDto = new Blob([dto], { type: "application/json" });
    formData.append("dto", changedDto);

    const changedTags = new Blob([hashtag]);
    formData.append("tags", changedTags);
    axios
      .post("http://localhost:8080/feed/add", formData)
      .then((res) => {
        console.log(res.data);
        navigate("/");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
      <Appbars
        username={props.username}
        setUsername={props.setUsername}></Appbars>
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{
              width: "50rem",
              margin: "1.2rem",
              padding: "0.8rem",
            }}>
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
              <TitleWrap>
                <TextField
                  id="standard-multiline-flexible"
                  label="해시태그"
                  multiline
                  maxRows={4}
                  variant="standard"
                  value={hashtag}
                  onChange={OCHashtag}
                />
              </TitleWrap>
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
                        ref={PnVRef}></input>
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
                        ref={musicRef}></input>
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
