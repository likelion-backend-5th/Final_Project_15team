import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import * as React from "react";
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
} from "@mui/material";

import HomeIcon from "@mui/icons-material/Home";
import SettingsIcon from "@mui/icons-material/Settings";
import AddIcon from "@mui/icons-material/Add";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";

let WholeWrap = styled.div``;

let TopWrap = styled.div`
  display: flex;
`;
let Icons = styled.div``;
let ContentWrap = styled.div``;

function ChatDelete() {
  const [selected, setSelected] = React.useState(false);
  return (
    <>
      <WholeWrap>
        <TopWrap>
          채팅
          <Icons>
            <AddIcon></AddIcon>
            <DeleteOutlineIcon></DeleteOutlineIcon>
          </Icons>
        </TopWrap>
        <ContentWrap>
          <List
            sx={{ width: "100%", maxWidth: 360, bgcolor: "background.paper" }}>
            <ListItem alignItems="flex-start">
              <ToggleButton
                value="check"
                selected={selected}
                onChange={() => {
                  setSelected(!selected);
                }}></ToggleButton>
              <ListItemAvatar>
                <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
              </ListItemAvatar>
              <ListItemText
                primary="채팅제목"
                secondary={<React.Fragment>채팅미리보기</React.Fragment>}
              />
              인원
            </ListItem>
            <Divider variant="inset" component="li" />
            <ListItem alignItems="flex-start">
              <ToggleButton
                value="check"
                selected={selected}
                onChange={() => {
                  setSelected(!selected);
                }}></ToggleButton>
              <ListItemAvatar>
                <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
              </ListItemAvatar>
              <ListItemText
                primary="채팅제목"
                secondary={<React.Fragment>채팅미리보기</React.Fragment>}
              />
              인원
            </ListItem>
            <Divider variant="inset" component="li" />
            <ListItem alignItems="flex-start">
              <ToggleButton
                value="check"
                selected={selected}
                onChange={() => {
                  setSelected(!selected);
                }}></ToggleButton>
              <ListItemAvatar>
                <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
              </ListItemAvatar>
              <ListItemText
                primary="채팅제목"
                secondary={<React.Fragment>채팅미리보기</React.Fragment>}
              />
              인원
            </ListItem>
          </List>
        </ContentWrap>
      </WholeWrap>
    </>
  );
}
export default ChatDelete;
