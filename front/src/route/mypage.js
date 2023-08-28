import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import Appbars from "../components/appbars";

import React, { useEffect, useState } from "react";
import {
  Box,
  Button,
  Paper,
  List,
  ListItem,
  ListItemText,
  Divider,
  IconButton,
} from "@mui/material";

import SettingsIcon from "@mui/icons-material/Settings";

let WholeWrap = styled.div`
  margin: 0.8rem;
`;
let ProfileImg = styled.div`
  border-radius: 2rem;
  background: grey;
  width: 4rem;
  height: 4rem;
`;
let FFS = styled.div`
  margin-left: 8rem;
  flex: 1;
`;
let TopWrap = styled.div`
  display: flex;
  padding: 1.2rem;
`;

function Mypage() {
  let navigate = useNavigate();
  const [follower] = useState(11);
  const [following] = useState(9);
  const [scrap] = useState(13);
  const [data, setData] = useState([]);
  // useEffect로 피드 데이터 불러오기
  useEffect(() => {
    axios
      .get("http://localhost:8080/feed")
      .then((res) => {
        console.log(res.data);
        setData(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <>
      <Appbars />
      <WholeWrap>
        <Paper elevation={3} style={{ padding: "0.8rem", margin: "0.8rem" }}>
          <TopWrap>
            <ProfileImg>프사</ProfileImg>
            <FFS>
              <tr style={{ fontSize: "1.6rem", textAlign: "center" }}>
                <th>
                  <IconButton
                    onClick={() => {
                      navigate("/follower");
                    }}
                  >
                    팔로워
                  </IconButton>
                </th>
                <th>|</th>
                <th>
                  <IconButton
                    onClick={() => {
                      navigate("/following");
                    }}
                  >
                    팔로잉
                  </IconButton>
                </th>
                <th>|</th>
                <th>
                  <IconButton
                    onClick={() => {
                      navigate("/scrap");
                    }}
                  >
                    스크랩
                  </IconButton>
                </th>
              </tr>
              <tr style={{ fontSize: "1.6rem", textAlign: "center" }}>
                <td>{follower}</td>
                <td>|</td>
                <td>{following}</td>
                <td>|</td>
                <td>{scrap}</td>
              </tr>
            </FFS>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={() => {
                navigate("/mypageset");
              }}
              color="inherit"
            >
              <SettingsIcon></SettingsIcon>
            </IconButton>
          </TopWrap>
          <Paper elevation={3} style={{ padding: "0.8rem", margin: "0.8rem" }}>
            <Box
              sx={{
                flexWrap: "wrap",
                "& > :not(style)": {
                  m: 1,
                },
              }}
            >
              <List
                sx={{
                  width: "100%",
                  // maxWidth: 360,
                  bgcolor: "background.paper",
                }}
              >
                {data.map(function (i, b) {
                  return (
                    <>
                      <Button
                        onClick={() => {
                          navigate("/feeddetail/" + i.id);
                        }}
                      >
                        <ListItem alignItems="flex-start">
                          <ListItemText primary={i.title} />
                        </ListItem>
                      </Button>
                      <Divider variant="inset" component="li" />
                    </>
                  );
                })}
              </List>
            </Box>
          </Paper>
        </Paper>
      </WholeWrap>
    </>
  );
}
export default Mypage;
