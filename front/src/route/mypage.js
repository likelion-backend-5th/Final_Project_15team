import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import React, { useEffect, useState } from "react";
import {
  Box,
  Paper,
  List,
  ListItem,
  ListItemText,
  Divider,
  Typography,
} from "@mui/material";

import HomeIcon from "@mui/icons-material/Home";
import SettingsIcon from "@mui/icons-material/Settings";

let WholeWrap = styled.div`
  margin: 2rem;
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
`;

function Mypage() {
  let navigate = useNavigate();
  const [follower, setFollower] = useState(11);
  const [following, setFollowing] = useState(9);
  const [scrap, setScrap] = useState(13);
  const [data, setData] = useState([]);
  // useEffect로 피드 데이터 불러오기
  useEffect(() => {
    axios.get("http://localhost:3000/sns").then((res) => {
      console.log(res.data);
      setData(res.data);
    });
  }, []);

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
          <ProfileImg>프사</ProfileImg>
          <FFS>
            <tr>
              <th>팔로워</th>
              <th>|</th>
              <th>팔로잉</th>
              <th>|</th>
              <th>스크랩</th>
            </tr>
            <tr>
              <td>{follower}</td>
              <td></td>
              <td>{following}</td>
              <td></td>
              <td>{scrap}</td>
            </tr>
          </FFS>
          <SettingsIcon
            onClick={() => {
              navigate("/mypageset");
            }}
            style={{ flex: "1", fontSize: "2rem" }}></SettingsIcon>
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
                    <ListItem alignItems="flex-start">
                      <ListItemText
                        primary={i.title}
                        secondary={
                          <React.Fragment>
                            <Typography
                              sx={{ display: "inline" }}
                              component="span"
                              variant="body2"
                              color="text.primary">
                              {i.content}
                            </Typography>
                          </React.Fragment>
                        }
                      />
                    </ListItem>
                    <Divider variant="inset" component="li" />
                  </>
                );
              })}
            </List>
          </Box>
        </Paper>
      </WholeWrap>
    </>
  );
}
export default Mypage;
