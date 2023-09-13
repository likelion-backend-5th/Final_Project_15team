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
  margin: auto;
  max-width: 700px;
`;
let ProfileImg = styled.div`
  border-radius: 2rem;
  background: grey;
  width: 4rem;
  height: 4rem;
`;
let FFS = styled.div`
  text-align: center;
`;
let TopWrap = styled.div`
  display: flex;
  padding: 1.2rem;
  justify-content: space-between;
`;

function Mypage(props) {
  let navigate = useNavigate();
  // const [follower] = useState(11);
  const [following, setFollowing] = useState([]);
  const [follower, setFollower] = useState([]);
  const [scrap, setScrap] = useState([]);
  const [data, setData] = useState([]);
  const [nickname, setNickname] = useState();
  const [introduction, setIntroduction] = useState();
  const [profileImage, setProfileImg] = useState();
  // useEffect로 피드 데이터 불러오기
  useEffect(() => {
    axios
      .get("http://localhost:8080/feed")
      .then((res) => {
        console.log(res.data);
        setData(res.data);
        setScrap(res.data.length);
      })
      .catch((error) => {
        console.log(error);
      });
    axios
      .get("http://localhost:8080/users/mypage/" + props.username + "/follow")
      .then((res) => {
        console.log(res.data);
        setFollowing(res.data.followingList.length);
        setNickname(res.data.nickname);
        setIntroduction(res.data.introduction);
        setProfileImg(res.data.profileImage);
        setFollower(res.data.followerList.length);
        console.log(following);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <>
      <Appbars username={props.username} setUsername={props.setUsername} />
      <WholeWrap>
        <Paper elevation={3} style={{ padding: "0.8rem", margin: "0.8rem" }}>
          <TopWrap>
            <div style={{ display: "block" }}>
              <ProfileImg>
                <img
                  src={profileImage}
                  style={{ width: "4rem", borderRadius: "4rem" }}
                />
              </ProfileImg>
              <div
                style={{
                  fontWeight: "bold",
                  fontSize: "1.2rem",
                }}>
                {nickname}
              </div>
              <div>{introduction}</div>
            </div>

            <FFS>
              <tr
                style={{
                  fontSize: "1.6rem",
                  textAlign: "center",
                }}>
                <th>
                  <IconButton
                    onClick={() => {
                      navigate("/follower");
                    }}>
                    팔로워
                  </IconButton>
                </th>
                <th>|</th>
                <th>
                  <IconButton
                    onClick={() => {
                      navigate("/following");
                    }}>
                    팔로잉
                  </IconButton>
                </th>
                <th>|</th>
                <th>
                  <IconButton
                    onClick={() => {
                      navigate("/scrap");
                    }}>
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
              color="inherit">
              <SettingsIcon style={{ fontSize: "4rem" }} />
            </IconButton>
          </TopWrap>
          <Paper elevation={3} style={{ padding: "0.8rem", margin: "0.8rem" }}>
            <Box
              sx={{
                flexWrap: "wrap",
                "& > :not(style)": {
                  m: 1,
                },
              }}>
              <List
                sx={{
                  width: "100%",
                  // maxWidth: 360,
                  bgcolor: "background.paper",
                }}>
                {data.map(function (i, b) {
                  return (
                    <>
                      <Button
                        onClick={() => {
                          navigate("/feeddetail/" + i.id);
                        }}>
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
