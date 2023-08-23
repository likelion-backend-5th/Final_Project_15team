import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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
  ToggleButton,
  Button,
} from "@mui/material";

import Appbars from "../components/appbars";

let WholeWrap = styled.div``;

let TopWrap = styled.div`
  display: flex;
`;
let Icons = styled.div``;
let ContentWrap = styled.div``;

function ChatDelete() {
  const [selected, setSelected] = React.useState(false);
  let navigate = useNavigate();
  let [data, setData] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/sns").then((res) => {
      console.log(res.data);
      setData(res.data);
    });
  }, []);
  return (
    <>
      <Appbars />
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{
              width: "50%",
              margin: "1.2rem",
              marginRight: "0.4rem",
              padding: " 1.2rem",
            }}>
            <TopWrap>
            <div style={{margin:"auto", fontSize:"1.6rem"}}>
              채팅
              </div>
              <Button
                variant="contained"
                onClick={() => {
                  navigate("/chatlist");
                }}>
                돌아가기
              </Button>
              <Button variant="contained">삭제하기</Button>
            </TopWrap>
            <ContentWrap>
              <List
                sx={{
                  width: "100%",
                  maxWidth: 360,
                  bgcolor: "background.paper",
                }}>
                {data.map(function (i, b) {
                  return (
                    <>
                      <ListItem alignItems="flex-start">
                        <ToggleButton
                          value="check"
                          selected={selected}
                          onChange={() => {
                            setSelected(!selected);
                          }}></ToggleButton>
                        <ListItemAvatar>
                          <Avatar
                            alt="Remy Sharp"
                            src="/static/images/avatar/1.jpg"
                          />
                        </ListItemAvatar>
                        <ListItemText
                          primary={i.title}
                          secondary={<React.Fragment>{i.chat}</React.Fragment>}
                        />
                        {i.chatcount}
                      </ListItem>
                      <Divider variant="inset" component="li" />
                    </>
                  );
                })}
              </List>
            </ContentWrap>
          </Paper>
        </Box>
      </WholeWrap>
    </>
  );
}
export default ChatDelete;
