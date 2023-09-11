import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Box, Stack, Paper, IconButton } from "@mui/material";
import axios from "axios";

import Comments from "./comments";

import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import AddCommentIcon from "@mui/icons-material/AddComment";
import BookmarkAddIcon from "@mui/icons-material/BookmarkAdd";
import ThumbUpAltIcon from "@mui/icons-material/ThumbUpAlt";

let FeedWrap = styled.div`
  // background: #00457e;
  background: #dfe9f5;
  margin: 2rem;
  border-radius: 1rem;
  padding: 2rem;
  box-shadow: 0.2rem 0.2rem 1rem black;
  // color: white;
  color: black;
`;
let LCwrap = styled.div`
  padding: 0.5rem;
`;

let HashTag = styled.div`
  padding: 0.5rem;
`;
let Title = styled.div`
  padding: 0.5rem;
  font-size: 1.5rem;
`;
let ProfileImg = styled.div`
  border-radius: 10rem;
  background: black;
  width: 2rem;
  float: left;
  font-size: 1.5rem;
  margin-right: 1rem;
`;
let Username = styled.div`
  font-weight: 1rem;
  font-size: 1.2rem;
`;
let Time = styled.div``;
let BottomBox = styled.div``;
let LikeText = styled.div`
  margin-left: 5rem;
`;
let Icons = styled.div`
  float: left;
`;
let Contents = styled.div`
  margin: 0.5rem;
`;

export default function Feedswrap() {
  const navigate = useNavigate();
  const [commentIndex, setCommentIndex] = useState([]);
  const [like, setLike] = useState(false);
  const likes = 10;
  const [data, setData] = useState([]);
  const [rerender, setRerender] = useState();
  const [hashtag, setHashtag] = useState([]);
  // const navToDetail = (id) => {
  //   navigate("/feeddetail/" + id);
  // };
  useEffect(() => {
    axios
      .get("http://localhost:8080/feed")
      .then((res) => {
        console.log(res.data);
        const reverse = res.data.reverse();
        setData(reverse);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [rerender]);
  return (
    <Box
      sx={{
        margin: "auto",
      }}>
      <Stack spacing={2}>
        {data.map(function (i, b) {
          return (
            <>
              <FeedWrap
              // onClick={() => {
              //   navToDetail(i.id);
              // }}
              >
                <Paper
                  elevation={3}
                  style={{
                    borderRadius: "1rem",
                    padding: "0.8rem",
                  }}>
                  <ProfileImg>
                    <img />
                  </ProfileImg>
                  <Username>{i.nickname}</Username>
                  <Time>
                    {i.date} {i.time}
                  </Time>
                </Paper>
                <div
                  style={{
                    background: "lightgrey",
                    margin: "auto",
                    borderRadius: "1rem",
                    height: "10rem",
                    marginTop: "0.8rem",
                    marginBottom: "0.8rem",
                  }}>
                  <img src={i.fileUrl} />a
                </div>
                <BottomBox>
                  <Paper
                    elevation={3}
                    style={{
                      borderRadius: "1rem",
                      padding: "0.8rem",
                    }}>
                    <LCwrap>
                      <Icons>
                        <IconButton
                          onClick={() => {
                            if (like) {
                              setLike(false);
                            } else {
                              setLike(true);
                            }
                          }}>
                          {like ? <ThumbUpAltIcon /> : <ThumbUpOffAltIcon />}
                        </IconButton>
                        <IconButton>
                          <AddCommentIcon
                            onClick={() => {
                              if (commentIndex.includes(b)) {
                                setCommentIndex([
                                  ...commentIndex.filter((item) => item !== b),
                                ]);
                              } else {
                                setCommentIndex([i.id, ...commentIndex]);
                              }
                            }}
                          />
                        </IconButton>
                        <IconButton>
                          <BookmarkAddIcon />
                        </IconButton>
                      </Icons>
                      <LikeText>{likes}명이 좋아합니다.</LikeText>
                    </LCwrap>
                    <Title>{i.title}</Title>
                    <Contents>{i.content}</Contents>
                    <HashTag>
                      {i.hashtag.map((a, b) => {
                        return <div>{a.tagName}</div>;
                      })}
                    </HashTag>
                    {commentIndex.includes(b) ? (
                      <>
                        <Comments
                          props={i.comments}
                          feed={i.id}
                          setRerender={setRerender}></Comments>
                      </>
                    ) : null}
                  </Paper>
                </BottomBox>
              </FeedWrap>
            </>
          );
        })}
      </Stack>
    </Box>
  );
}
