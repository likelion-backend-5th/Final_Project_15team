import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";
import { useParams } from "react-router-dom";
import React, { useState, useEffect, useRef } from "react";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";
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

  // let stompClient = null;
  // stompClient에 서버 연결, 메시지 전송, 구독 관련 값을 추가 할당하게 됨
  let stompClient = useRef({});
  const [chat, setChat] = useState("");
  const [msg, setMsg] = useState([]);

  // 소켓 연결 함수
  const connect = () => {
    // 소켓 연결 설정
    let socket = new SockJS("http://localhost:8080/ws/chat");
    stompClient.current = Stomp.over(socket);

    console.log("소켓연결 성공");
    console.log("roomId = " + id);
    // 소켓 연결
    stompClient.current.connect({}, onConnected);
  };

  // 소켓 연결 후 구독 시작
  const onConnected = () => {
    console.log("구독 시작");
    // sub 할 url => /sub/chat/room/roomId 로 구독한다
    // msgRecv는 해당 토픽을 구독하는 사용자에게 메세지가(응답값) 왔을때 실행되는 콜백 함수
    stompClient.current.subscribe("/topic/chat/room/enter/" + id, msgRecv);

    // /pub/chat/enterUser 로 Json 형태의 메시지를 보냄
    stompClient.current.send(
      "/app/chat/enter",
      {},
      JSON.stringify({
        roomId: id,
        sender: "testuser",
        type: "ENTER",
      })
    );
  };

  let chatMessages = document.getElementById("chat-messages");
  const msgRecv = (payload) => {
    let getMsg = JSON.parse(payload.body);
    let messageElement = document.createElement("div");
    switch (getMsg.type) {
      case "ENTER":
        messageElement.textContent = getMsg.message;
        console.log(getMsg.message);
        break;
      case "EXIT":
        messageElement.textContent = getMsg.message;

        break;
      case "TALK":
        messageElement.textContent = getMsg.message;
        console.log(getMsg.message);
        break;
    }
    // 채팅 메시지 요소를 채팅 영역에 추가
    chatMessages.appendChild(messageElement);
    // 채팅 영역의 스크롤 위치를 맨 아래로 이동시켜서 최신 메시지를 표시
    chatMessages.scrollTop = chatMessages.scrollHeight;
    // setMsg((msg) => [...msg, getMsg]);
  };

  const handleChange = (e) => {
    setChat(e.target.value);
  };

  const sendMessage = (e) => {
    if (chat && stompClient) {
      let chatMessage = {
        roomId: id,
        sender: "testuser",
        message: chat,
        type: "TALK",
      };
      stompClient.current.send(
        "/app/chat/message",
        {},
        JSON.stringify(chatMessage)
      );
      setChat("");
    }

    e.preventDefault();
  };

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
              <div id="chat-messages"></div>
              {/* {msg.map((i, b) => {
                return <div>{i.message}</div>;
              })} */}
            </ContentWrap>
            <BottomWrap>
              <AddIcon></AddIcon>
              <TextInput>
                <TextField
                  value={chat}
                  name={"chatInput"}
                  onChange={handleChange}></TextField>
              </TextInput>
              <Button variant="contained" onClick={sendMessage}>
                보내기
              </Button>
              <Button variant="contained" onClick={connect}>
                접속하기
              </Button>
            </BottomWrap>
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default ChatPage;
