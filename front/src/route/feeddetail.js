import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import React, { useState, useEffect } from "react";
import axios from 'axios'
import {
  Button,
  Menu,
  MenuItem,
  Box,
  Paper,
  IconButton,
  Icon
} from "@mui/material";

import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import ContentCutIcon from "@mui/icons-material/ContentCut";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";

import Appbars from "../components/appbars";

let WholeWrap = styled.div``;
let TopWrap = styled.div`
  display: flex;
  padding: 0.4rem;
`;
let ContentWrap = styled.div``;
let BottomWrap = styled.div`
  padding: 0.4rem;
`;

let ProfileImg = styled.div`
  background: grey;
  border-radius: 2rem;
  width: 2rem;
  height: 2rem;
`;
let Title = styled.div`
  font-size: 2rem;
`;
let Content = styled.div``;
let Hashtag = styled.div``;

function FeedDetail() {
  const [data, setData] = useState("");
  useEffect(()=>{
    axios.get('http://localhost:8080/')
  },[])


  const deleteFeed = () => {
   axios.delete("http://localhost:8080/")
   setAnchorEl(null)
  }

  let navigate = useNavigate();
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
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
              <IconButton>
              <MoreHorizIcon
                id="basic-button"
                aria-controls={open ? "basic-menu" : undefined}
                aria-haspopup="true"
                aria-expanded={open ? "true" : undefined}
                onClick={handleClick}></MoreHorizIcon>

              <Menu
                id="basic-menu"
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                MenuListProps={{
                  "aria-labelledby": "basic-button",
                }}>
                <MenuItem onClick={() => {
                      navigate("/updatefeed");
                    }}>수정하기</MenuItem>
                <MenuItem onClick={deleteFeed}>삭제하기</MenuItem>
              </Menu>
              </IconButton>
            </TopWrap>
            <ContentWrap>
              <Paper
                elevation={3}
                style={{
                  margin: "0.4rem",
                  padding: "0.8rem",
                }}>
                <Title>제목:굵은글씨</Title>
              </Paper>
              <Paper
                elevation={3}
                style={{
                  margin: "0.4rem",
                  padding: "0.8rem",
                }}>
                <Content>
                  동해물과백두산이마르고닳도록하느님이보우하사우리나라만세
                  동해물과백두산이마르고닳도록하느님이보우하사우리나라만세
                  동해물과백두산이마르고닳도록하느님이보우하사우리나라만세
                  동해물과백두산이마르고닳도록하느님이보우하사우리나라만세
                  동해물과백두산이마르고닳도록하느님이보우하사우리나라만세
                  동해물과백두산이마르고닳도록하느님이보우하사우리나라만세
                </Content>
              </Paper>
              <Paper
                elevation={3}
                style={{
                  margin: "0.4rem",
                  padding: "0.8rem",
                }}>
                <Hashtag>#해시태그</Hashtag>
              </Paper>
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
export default FeedDetail;
