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
  IconButton,
  Checkbox,
} from "@mui/material";

import RefreshIcon from "@mui/icons-material/Refresh";
import RestoreFromTrashIcon from "@mui/icons-material/RestoreFromTrash";

import Appbars from "../components/appbars";

let WholeWrap = styled.div``;

let TopWrap = styled.div`
  display: flex;
`;
// let Icons = styled.div``;
let ContentWrap = styled.div``;

function ChatDelete(props) {
  let navigate = useNavigate();
  let [data, setData] = useState([]);
  let [del, setDel] = useState([]);

  const deleteAll = () => {
    del.map(function (i, b) {
      axios
        .delete("http://localhost:8080/chat/rooms/" + i + "/delete")
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    });
    navigate("/chatlist");
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/chat/rooms")
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
      <Appbars username={props.username} setUsername={props.setUsername} />
      <WholeWrap>
        <Box style={{ display: "flex" }}>
          <Paper
            elevation={3}
            style={{
              width: "100%",
              margin: "auto 0.4rem",
              marginTop: "0.4rem",
              padding: " 1.2rem",
            }}>
            <TopWrap>
              <div style={{ margin: "auto", fontSize: "1.6rem" }}>채팅</div>
              <IconButton>
                <RefreshIcon
                  onClick={() => {
                    navigate("/chatlist");
                  }}
                />
              </IconButton>
              <IconButton>
                <RestoreFromTrashIcon onClick={deleteAll} />
              </IconButton>
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
                        <Checkbox
                          onChange={() => {
                            setDel(Array.from(del));
                            if (del.includes(i.id)) {
                              setDel([...del.filter((item) => item !== i.id)]);
                            } else {
                              setDel([i.id, ...del]);
                            }
                          }}
                        />
                        <ListItemAvatar>
                          <Avatar
                            alt="Remy Sharp"
                            src="/static/images/avatar/1.jpg"
                          />
                        </ListItemAvatar>
                        <ListItemText
                          primary={i.roomName}
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
