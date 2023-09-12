import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import React, { useState, useRef, useEffect } from "react";
import { Button, Grid, TextField, Box, Paper } from "@mui/material";

import Appbars from "../components/appbars";

let WholeWrap = styled.div`
  margin: auto;
  max-width: 1000px;
`;

let TopWrap = styled.div`
  text-align: center;
  font-size: 1.6rem;
  margin: 0.8rem;
`;
let ProfileWrap = styled.div`
  text-align: center;
`;
let ProfileImg = styled.div`
  margin: auto;
  border-radius: 10rem;
  background: grey;
  width: 10rem;
  height: 10rem;
`;
let BottomWrap = styled.div``;

function MypageSet(props) {
  let navigate = useNavigate();
  const [nickname, setNickname] = useState("");
  const [intro, setIntro] = useState("");
  const [viewImgEdit, setViewImgEdit] = useState(false);
  const [imgUrl, setImgUrl] = useState();
  const imgUrlRef = useRef();
  const [data, setData] = useState();
  const [rerender, setRerender] = useState();

  useEffect(() => {
    console.log(props.username);
    axios
      .get("http://localhost:8080/users/mypage/" + props.username + "/profile")
      .then((res) => {
        console.log(res.data);
        setData(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [rerender]);

  const OCNickname = (e) => {
    setNickname(e.target.value);
  };
  const OCIntro = (e) => {
    setIntro(e.target.value);
  };
  const OCImgEdit = () => {
    if (viewImgEdit) {
      setViewImgEdit(false);
    } else {
      setViewImgEdit(true);
    }
  };

  const uploadImg = (e) => {
    e.preventDefault();
    if (e.target.files) {
      const uploadImg = e.target.files[0];
      setImgUrl(uploadImg);
    }
  };

  const setProfileImg = () => {
    const formData = new FormData();
    const changedImgData = new Blob([imgUrl], { type: "multipart/form-data" });
    formData.append("image", changedImgData);
    axios
      .put("http://localhost:8080/users/mypage/profile/imgupload", formData)
      .then((res) => {
        console.log(res.data);
        setRerender(Math.random());
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let body = {
      nickname: nickname,
      intro: intro,
    };
    axios
      .post("http://localhost:8080/feed", {}, { params: body })
      .then((res) => {
        console.log(res.data);
      })
      .catch((e) => {
        console.log(e.res);
        return "필수 데이터 채웠나요?";
      });
  };

  return (
    <>
      <Appbars
        username={props.username}
        setUsername={props.setUsername}></Appbars>
      <WholeWrap>
        <Box style={{ marginTop: "1.2rem", display: "flex" }}>
          <Paper
            elevation={3}
            style={{
              marginRight: "0.4rem",
              margin: "auto",
            }}>
            <TopWrap>프로필 편집</TopWrap>
            <ProfileWrap>
              <ProfileImg>
                {data ? <img src={data.profileImage} /> : null}
              </ProfileImg>
              <Button onClick={OCImgEdit}>대표이미지 수정</Button>

              {viewImgEdit ? (
                <>
                  <form>
                    <input
                      type="file"
                      onChange={uploadImg}
                      ref={imgUrlRef}></input>
                    <Button onClick={setProfileImg}>변경</Button>
                  </form>
                </>
              ) : null}
            </ProfileWrap>
            <BottomWrap>
              <Box
                component="form"
                noValidate
                onSubmit={handleSubmit}
                sx={{ mt: 3 }}>
                <Paper
                  elevation={3}
                  style={{ margin: "0.8rem", padding: "0.8rem" }}>
                  <Grid container spacing={2}>
                    <Grid item xs={12}>
                      <TextField
                        autoComplete="Nickname"
                        name="Nickname"
                        required
                        fullWidth
                        id="Nickname"
                        label="닉네임"
                        autoFocus
                        value={nickname}
                        onChange={OCNickname}
                      />
                    </Grid>
                    <Grid item xs={12}>
                      <TextField
                        autoComplete="Introduce"
                        name="Introduce"
                        fullWidth
                        id="Introduce"
                        label="소개"
                        autoFocus
                        value={intro}
                        onChange={OCIntro}
                      />
                    </Grid>
                  </Grid>
                </Paper>
                <Button
                  variant="contained"
                  style={{ marginTop: "0.4rem" }}
                  type="submit"
                  onClick={() => {
                    navigate("/mypage");
                  }}>
                  확인/저장
                </Button>
              </Box>
            </BottomWrap>
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default MypageSet;
