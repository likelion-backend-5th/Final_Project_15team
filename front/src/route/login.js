import React, { useState } from "react";
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
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Cookies } from "react-cookie";

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

// TODO remove, this demo shouldn't need to reset the theme.

const defaultTheme = createTheme();

export default function Login() {
  let navigate = useNavigate();
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  // 쿠키 설정
  const cookies = new Cookies();
  //쿠키에 값을 저장할때
  const setCookie = (name, value, option) => {
    return cookies.set(name, value, { ...option });
  };
  // //쿠키에 있는 값을 꺼낼때
  // const getCookie = (name) => {
  //   return cookies.get(name);
  // };
  // //쿠키를 지울때
  // const removeCookie = (name) => {
  //   return cookies.remove(name);
  // };
  // 로그인 POST 요청
  const LoginFunction = (e) => {
    e.preventDefault();
    if (!id) {
      return alert("아이디를 입력하세요.");
    } else if (!password) {
      return alert("비밀번호를 입력하세요.");
    } else {
      let body = { username: id, password: password };
      axios
        .post("http://localhost:8080/users/login", body)
        .then((res) => {
          console.log(res.data);
          const accessToken = res.data.token;
          setCookie("is_login", `${accessToken}`);
          console.log(accessToken);
          axios.defaults.headers.common[
            "Authorization"
          ] = `Bearer ${accessToken}`;
          navigate("/");
        })
        .catch((e) => {
          console.log(e);
          return "아이디, 패스워드 확인";
        });
    }
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
            로그인
          </Typography>
          <Box
            component="form"
            onSubmit={LoginFunction}
            noValidate
            sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="아이디"
              name="email"
              autoComplete="email"
              autoFocus
              value={id}
              onChange={(e) => setId(e.target.value)}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="비밀번호"
              type="password"
              id="password"
              autoComplete="current-password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {/* <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            /> */}

            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}>
              로그인
            </Button>
            <Grid container>
              {/* <Grid item xs>
                <Link href="#" variant="body2">
                  비밀번호 찾기
                </Link>
              </Grid> */}
              <Grid item>
                <Link
                  href=""
                  variant="body2"
                  onClick={() => {
                    navigate("/register");
                  }}>
                  {"회원가입"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 8, mb: 4 }} />
      </Container>
    </ThemeProvider>
  );
}
