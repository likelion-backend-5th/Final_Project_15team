import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import React, { useState, useEffect } from "react";
import { Button, Menu, MenuItem, Box, Paper } from "@mui/material";

import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import ContentCutIcon from "@mui/icons-material/ContentCut";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";

import Appbars from "../components/appbars";

let WholeWrap = styled.div``;
let TopWrap = styled.div`
  display: flex;
`;
let ContentWrap = styled.div``;
let BottomWrap = styled.div``;

let ProfileImg = styled.div`
  background: grey;
  border-radius: 2rem;
  width: 4rem;
  height: 4rem;
`;
let Title = styled.div`
  font-size: 2rem;
`;
let Content = styled.div``;
let Hashtag = styled.div``;

function FeedDetail() {
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
                <MenuItem onClick={handleClose}>수정하기</MenuItem>
                <MenuItem onClick={handleClose}>삭제하기</MenuItem>
              </Menu>
            </TopWrap>
            <ContentWrap>
              <Title>제목:굵은글씨</Title>
              <Content>내용</Content>
              <Hashtag>#해시태그</Hashtag>
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
