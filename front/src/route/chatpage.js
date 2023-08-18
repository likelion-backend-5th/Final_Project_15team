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
  Button,
} from "@mui/material";

import AddIcon from "@mui/icons-material/Add";

import Appbars from "../components/appbars";

let WholeWrap = styled.div``;
let TopWrap = styled.div`
  text-align: center;
  font-size: 2rem;
  padding: 0.4rem;
`;
let ContentWrap = styled.div``;
let BottomWrap = styled.div`
  display: flex;
`;
let TextInput = styled.div``;

let LeftChat = styled.div`
  display: flex;
  text-align: left;
`;
let RightChat = styled.div`
  display: flex;
  text-align: right;
`;

let ProfileImg = styled.div`
  background: grey;
  border-radius: 2rem;
  width: 2rem;
  height: 2rem;
`;

let ChatContent = styled.div``;
let ChatDate = styled.div``;

function ChatPage() {
  return (
    <>
      <Appbars></Appbars>
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{ width: "50%", margin: "1.2rem", marginRight: "0.4rem" }}>
            <TopWrap>채팅 제목</TopWrap>
            <ContentWrap>
              <LeftChat>
                <ProfileImg></ProfileImg>
                <ChatContent>안녕안녕</ChatContent>
                <ChatDate>23.08.08</ChatDate>
              </LeftChat>
              <RightChat>
                <ChatContent>안녕</ChatContent>
                <ChatDate>23.08.08</ChatDate>
                <ProfileImg></ProfileImg>
              </RightChat>
            </ContentWrap>
            <BottomWrap>
              <AddIcon></AddIcon>
              <TextInput>채팅내용</TextInput>
              <Button variant="contained">보내기</Button>
            </BottomWrap>
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default ChatPage;
