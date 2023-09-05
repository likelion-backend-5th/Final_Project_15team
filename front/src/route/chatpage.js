import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";
import { useParams } from "react-router-dom";
import React, { useState, useEffect, useRef } from "react";
import * as StompJs from "@stomp/stompjs";
import { Box, Paper, Button, TextField } from "@mui/material";

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
  const { id } = useParams();
  const [data, setData] = useState([]);
  const [me, setMe] = useState();
  const [msg, setMsg] = useState();
  const msgRef = useRef();

  const [chatList, setChatList] = useState([]);
  const [chat, setChat] = useState("");
  const client = useRef({});
  const { apply_id } = useParams();
  const connect = () => {
    console.log(id);
    client.current = new StompJs.Client({
      brokerURL: "ws://localhost:8080/chatting",
      onConnect: () => {
        subscribe();
      },
    });
    client.current.activate();
  };
  const publish = (chat) => {
    if (!client.current.connected) return;

    client.current.publish({
      destination: "/pub",
      body: JSON.stringify({
        applyId: apply_id,
        chat: chat,
      }),
    });

    setChat("");
  };
  const subscribe = () => {
    client.current.subscribe("/sub" + apply_id, (body) => {
      const json_body = JSON.parse(body.body);
      setChatList((_chat_list) => [..._chat_list, json_body]);
    });
  };

  const disconnect = () => {
    client.current.deactivate();
  };

  const handleChange = (event) => {
    // 채팅 입력 시 state에 값 설정
    setChat(event.target.value);
  };

  const handleSubmit = (event, chat) => {
    // 보내기 버튼 눌렀을 때 publish
    event.preventDefault();

    publish(chat);
  };

  useEffect(() => {
    connect();

    return () => disconnect();
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
            <TopWrap>{data.roomName}</TopWrap>
            <ContentWrap>
              {data.map(function (i, b) {
                if (i.sender !== me) {
                  return (
                    <LeftChat>
                      <ProfileImg></ProfileImg>
                      <ChatContent>{i.message}</ChatContent>
                      <ChatDate>{i.time}</ChatDate>
                    </LeftChat>
                  );
                } else {
                  return (
                    <RightChat>
                      <ChatContent>{i.message}</ChatContent>
                      <ChatDate>{i.time}</ChatDate>
                      <ProfileImg></ProfileImg>
                    </RightChat>
                  );
                }
              })}
            </ContentWrap>
            <BottomWrap>
              <AddIcon></AddIcon>
              <TextInput>
                <TextField
                  value={chat}
                  name={"chatInput"}
                  onChange={handleChange}></TextField>
              </TextInput>
              <Button
                variant="contained"
                type={"submit"}
                onClick={handleSubmit}>
                보내기
              </Button>
            </BottomWrap>
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default ChatPage;
