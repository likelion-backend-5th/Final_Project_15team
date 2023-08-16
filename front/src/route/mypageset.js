import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

import { Button, Grid, TextField } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";

let WholeWrap = styled.div`
  margin: 2rem;
`;

let TopWrap = styled.div``;
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
  return (
    <>
      <HomeIcon
        style={{
          color: "white",
          fontSize: "2.8rem",
          background: "blue",
          margin: "0.8rem",
          padding: "0.4rem",
          borderRadius: "0.4rem",
        }}></HomeIcon>
      <WholeWrap>
        <TopWrap>
          프로필 편집
          <Button
            variant="contained"
            style={{ float: "right", marginTop: "0.4rem" }}>
            확인/저장
          </Button>
        </TopWrap>
        <ProfileWrap>
          <ProfileImg>ㅁ</ProfileImg>
          대표이미지 수정
        </ProfileWrap>
        <BottomWrap>
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
              />{" "}
              <Button
                variant="contained"
                style={{ float: "right", marginTop: "0.4rem" }}>
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
              />
            </Grid>
          </Grid>
        </BottomWrap>
      </WholeWrap>
    </>
  );
}
export default MypageSet;
