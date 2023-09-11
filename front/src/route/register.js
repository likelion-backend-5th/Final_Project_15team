import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  Link,
  Grid,
  Box,
  Typography,
  Container,
} from "@mui/material";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import { createTheme, ThemeProvider } from "@mui/material/styles";

function Copyright(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}>
      {"Copyright © "}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const defaultTheme = createTheme();

export default function Register() {
  let navigate = useNavigate();
  const [name, setName] = useState("");
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [checkpassword, setCheckpassword] = useState("");
  const [nickname, setNickname] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");

  const OCName = (e) => {
    setName(e.target.value);
  };
  const OCId = (e) => {
    setId(e.target.value);
  };
  const OCPassword = (e) => {
    setPassword(e.target.value);
  };
  const OCCPassword = (e) => {
    setCheckpassword(e.target.value);
  };
  const OCNickname = (e) => {
    setNickname(e.target.value);
  };
  const OCPhone = (e) => {
    setPhone(e.target.value);
  };
  const OCEmail = (e) => {
    setEmail(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let body = {
      name: name,
      username: id,
      password: password,
      "password-check": password,
      nickname: nickname,
      phonenumber: phone,
      email: email,
    };
    axios
      .post("http://localhost:8080/users/register", {}, { params: body })
      .then((res) => {
        console.log(res.data);
        navigate("/login");
      })
      .catch((e) => {
        console.log(e);
        return "필수 데이터 채웠나요?";
      });
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}>
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            회원가입
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  autoComplete="name"
                  name="name"
                  required
                  fullWidth
                  id="Name"
                  label="이름"
                  autoFocus
                  value={name}
                  onChange={OCName}
                />
              </Grid>

              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="id"
                  label="아이디"
                  name="id"
                  autoComplete="id"
                  value={id}
                  onChange={OCId}
                />
                {/* <Button
                  variant="contained"
                  type="button"
                  style={{ float: "right", marginTop: "0.4rem" }}>
                  중복확인
                </Button> */}
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="nickname"
                  label="닉네임"
                  name="nickname"
                  autoComplete="nickname"
                  value={nickname}
                  onChange={OCNickname}
                />
                {/* <Button
                  variant="contained"
                  type="button"
                  style={{ float: "right", marginTop: "0.4rem" }}>
                  중복확인
                </Button> */}
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="비밀번호"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                  value={password}
                  onChange={OCPassword}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="checkPassword"
                  label="비밀번호 확인"
                  type="checkPassword"
                  id="checkPassword"
                  autoComplete="checkPassword"
                  value={checkpassword}
                  onChange={OCCPassword}
                />
                {password === "" ? null : (
                  <>
                    {password !== checkpassword ? (
                      <div style={{ color: "red" }}>
                        비밀번호가 일치하지않습니다.
                      </div>
                    ) : (
                      <div style={{ color: "blue" }}>
                        비밀번호가 일치합니다.
                      </div>
                    )}
                  </>
                )}
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="phone"
                  label="전화번호"
                  type="phone"
                  id="phone"
                  autoComplete="phone"
                  value={phone}
                  onChange={OCPhone}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="email"
                  label="이메일"
                  type="email"
                  id="email"
                  autoComplete="email"
                  value={email}
                  onChange={OCEmail}
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}>
              회원가입
            </Button>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
    </ThemeProvider>
  );
}
