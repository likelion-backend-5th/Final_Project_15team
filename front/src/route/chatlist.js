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
  Button,
  Modal,
  TextField,
} from "@mui/material";

import HomeIcon from "@mui/icons-material/Home";
import SettingsIcon from "@mui/icons-material/Settings";
import AddIcon from "@mui/icons-material/Add";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

let WholeWrap = styled.div``;

let TopWrap = styled.div`
  display: flex;
`;
let Icons = styled.div``;
let ContentWrap = styled.div``;

function ChatList() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  return (
    <>
      <WholeWrap>
        <TopWrap>
          채팅
          <Icons>
            <AddIcon onClick={handleOpen}></AddIcon>
            <Modal
              open={open}
              onClose={handleClose}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description">
              <Box sx={style}>
                <Typography id="modal-modal-title" variant="h6" component="h2">
                  채팅방 생성
                </Typography>
                <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                  <TextField
                    style={{ margin: "0.4rem" }}
                    id="title"
                    label="제목"
                    varient="outlined"></TextField>
                  <TextField
                    style={{ margin: "0.4rem" }}
                    id="count"
                    label="인원수"
                    varient="outlined"></TextField>
                  <div style={{ margin: "0.4rem" }}>
                    대표이미지<AddIcon></AddIcon>
                  </div>
                </Typography>
              </Box>
            </Modal>
            <DeleteOutlineIcon></DeleteOutlineIcon>
          </Icons>
        </TopWrap>
        <ContentWrap>
          <List
            sx={{ width: "100%", maxWidth: 360, bgcolor: "background.paper" }}>
            <ListItem alignItems="flex-start">
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
export default ChatList;
