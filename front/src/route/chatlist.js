import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import React, { useState, useEffect, useRef } from "react";
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
  IconButton,
} from "@mui/material";

import AddIcon from "@mui/icons-material/Add";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";

import Appbars from "../components/appbars";

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
  text-align: center;
`;
let Icons = styled.div``;
let ContentWrap = styled.div``;

function ChatList() {
  let navigate = useNavigate();
  let [data, setData] = useState([]);
  const [imgUrl, setImgUrl] = useState();
  const imgUrlRef = useRef();
  const [viewUploadImg, setViewUploadImg] = useState();

  const uploadImg = (e) => {
    e.preventDefault();
    if (e.target.files) {
      const uploadImg = e.target.files[0];
      setImgUrl(uploadImg);
    }
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/chat/rooms")
      .then((res) => {
        console.log("채팅방 get요청");
        console.log(res.data);
        setData(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [roomName, setRoomName] = useState();
  const createChat = (e) => {
    e.preventDefault();
    const formData = new FormData();
    const changedImgData = new Blob([imgUrl], { type: "multipart/form-data" });
    formData.append("image", changedImgData);
    const roomData = JSON.stringify({ roomName: roomName });
    const changedRoomData = new Blob([roomData], { type: "application/json" });
    formData.append("chatRoomDto", changedRoomData);
    axios
      .post("http://localhost:8080/chat/rooms", formData)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
    setOpen(false);
  };
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
              <div style={{ margin: "auto", fontSize: "1.6rem" }}>채팅</div>
              <Icons>
                <IconButton>
                  <AddIcon onClick={handleOpen}></AddIcon>
                </IconButton>
                <Modal
                  open={open}
                  onClose={handleClose}
                  aria-labelledby="modal-modal-title"
                  aria-describedby="modal-modal-description">
                  <Box sx={style}>
                    <Typography
                      id="modal-modal-title"
                      variant="h6"
                      component="h2">
                      채팅방 생성
                    </Typography>
                    <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                      <TextField
                        style={{ margin: "0.4rem" }}
                        id="title"
                        label="방이름"
                        varient="outlined"
                        value={roomName}
                        onChange={(e) =>
                          setRoomName(e.target.value)
                        }></TextField>
                      {/* <TextField
                        style={{ margin: "0.4rem" }}
                        id="count"
                        label="방장"
                        varient="outlined"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                      ></TextField> */}
                      <div style={{ margin: "0.4rem" }}>
                        대표이미지
                        <IconButton>
                          <AddIcon
                            onClick={() => {
                              if (viewUploadImg) {
                                setViewUploadImg(false);
                              } else {
                                setViewUploadImg(true);
                              }
                            }}></AddIcon>
                        </IconButton>
                        {viewUploadImg ? (
                          <form>
                            <input
                              type="file"
                              onChange={uploadImg}
                              ref={imgUrlRef}></input>
                          </form>
                        ) : null}
                      </div>
                      <Button variant="contained" onClick={createChat}>
                        생성하기
                      </Button>
                    </Typography>
                  </Box>
                </Modal>
                <IconButton
                  onClick={() => {
                    navigate("/chatdelete");
                  }}>
                  <DeleteOutlineIcon></DeleteOutlineIcon>
                </IconButton>
              </Icons>
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
                      <Button
                        onClick={() => {
                          navigate("/chatpage/" + i.id);
                        }}>
                        <ListItem alignItems="flex-start">
                          <ListItemAvatar>
                            <Avatar alt="Remy Sharp" src={i.imageUrl} />
                          </ListItemAvatar>
                          <ListItemText
                            primary={i.roomName}
                            secondary={
                              <React.Fragment>
                                {i.chat}
                                {i.chatcount}명
                              </React.Fragment>
                            }></ListItemText>
                        </ListItem>
                        <Divider variant="inset" component="li" />
                      </Button>
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
export default ChatList;
