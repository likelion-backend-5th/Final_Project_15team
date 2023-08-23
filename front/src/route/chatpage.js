import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";

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
  const [chat, setChat] = useState([]);
  useEffect(() => {
    axios.get("http://localhost:8080/sns").then((res) => {
      console.log(res.data);
      setChat(res.data);
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
            <TopWrap>채팅 제목</TopWrap>
            <ContentWrap>
              {chat.map(function (i, b) {
                return (
                  <>
                    <LeftChat>
                      <ProfileImg></ProfileImg>
                      <ChatContent>{i.chat}</ChatContent>
                      <ChatDate>{i.date}</ChatDate>
                    </LeftChat>
                    <RightChat>
                      <ChatContent>{i.chat}</ChatContent>
                      <ChatDate>{i.date}</ChatDate>
                      <ProfileImg></ProfileImg>
                    </RightChat>
                  </>
                );
              })}
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
