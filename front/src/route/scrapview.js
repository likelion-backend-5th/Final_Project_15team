import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import React, { useState, useEffect } from "react";
import axios from "axios";

import {
  Box,
  Paper,
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  Avatar,
  Divider,
  Typography,
} from "@mui/material";

import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import ContentCutIcon from "@mui/icons-material/ContentCut";

import Appbars from "../components/appbars";

let WholeWrap = styled.div``;
let TopWrap = styled.div`
  display: flex;
`;
let ContentWrap = styled.div``;
let BottomWrap = styled.div``;

let ProfileImg = styled.div`
  background: grey;
  border-radius: 2rem;
  width: 2rem;
  height: 2rem;
`;
let Title = styled.div`
  font-size: 2rem;
`;
let Content = styled.div``;
let Hashtag = styled.div``;

function ScrapView() {
  const [data, setData] = useState([]);
  useEffect(() => {
    axios.get("http://localhost:8080/sns").then((res) => {
      console.log(res.data);
      setData(res.data[0]);
    });
  }, []);
  return (
    <>
      <Appbars></Appbars>
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{
              width: "50%",
              margin: "1.2rem",
              marginRight: "0.4rem",
              padding: "1.2rem",
            }}>
            <TopWrap>
              <ProfileImg>a</ProfileImg>
              {data.nickname}
            </TopWrap>
            <ContentWrap>
              <Paper
                elevation={3}
                style={{
                  margin: "0.4rem",
                  padding: "0.8rem",
                }}>
                <Title>{data.title}</Title>
                <Content>{data.content}</Content>
                <Hashtag>#해시태그</Hashtag>
              </Paper>
            </ContentWrap>
            <BottomWrap>
              <ThumbUpOffAltIcon></ThumbUpOffAltIcon>
              <ContentCutIcon></ContentCutIcon>
            </BottomWrap>
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}

export default ScrapView;
