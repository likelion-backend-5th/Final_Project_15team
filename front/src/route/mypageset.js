import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import { Button, Grid, TextField, Box, Paper } from "@mui/material";

import Appbars from "../components/appbars";

let WholeWrap = styled.div`
  margin: 2rem;
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

function MypageSet() {
  let navigate = useNavigate();
  const [nickname, setNickname] = useState("");
  const [intro, setIntro] = useState("");

  const OCNickname = (e) => {
    setNickname(e.target.value);
  };
  const OCIntro = (e) => {
    setIntro(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let body = {
      nickname: nickname,
      intro: intro,
    };
    axios
      .post("http://localhost:8080/feed", body)
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
      <Appbars></Appbars>
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{ width: "50%", margin: "1.2rem", marginRight: "0.4rem" }}>
            <TopWrap>프로필 편집</TopWrap>
            <ProfileWrap>
              <ProfileImg>ㅁ</ProfileImg>
              대표이미지 수정
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
                      <Button
                        variant="contained"
                        style={{ marginTop: "0.4rem" }}>
                        중복확인
                      </Button>
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
                  <Button
                    variant="contained"
                    style={{ marginTop: "0.4rem" }}
                    type="submit"
                    onClick={() => {
                      navigate("/mypage");
                    }}>
                    확인/저장
                  </Button>
                </Paper>
              </Box>
            </BottomWrap>
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default MypageSet;
