import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import React, { useState, useEffect } from "react";

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
  width: 4rem;
  height: 4rem;
`;
let Title = styled.div`
  font-size: 2rem;
`;
let Content = styled.div``;
let Hashtag = styled.div``;

function ScrapView() {
  return (
    <>
      <Appbars></Appbars>
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{ width: "50%", margin: "1.2rem", marginRight: "0.4rem" }}>
            <TopWrap>
              <ProfileImg>a</ProfileImg>
              이름
            </TopWrap>
            <ContentWrap>
              <Title>제목:굵은글씨</Title>
              <Content>내용</Content>
              <Hashtag>#해시태그</Hashtag>
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
