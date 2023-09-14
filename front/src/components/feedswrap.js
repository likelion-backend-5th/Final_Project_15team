import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Box, Button, Paper, IconButton } from "@mui/material";
import axios from "axios";

import Comments from "./comments";

import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import AddCommentIcon from "@mui/icons-material/AddComment";
import BookmarkAddIcon from "@mui/icons-material/BookmarkAdd";
import ThumbUpAltIcon from "@mui/icons-material/ThumbUpAlt";

let FeedWrap = styled.div`
  background: #dfe9f5;
  margin-top: 1.2rem;
  border-radius: 1rem;
  padding: 2rem;
  box-shadow: 0.2rem 0.2rem 1rem black;
  color: black;
  width: 100%;
`;
let LCwrap = styled.div`
  padding: 0.5rem;
`;

let HashTag = styled.div`
  padding: 0.5rem;
  display: flex;
`;
let Title = styled.div`
  padding: 0.5rem;
  font-size: 1.5rem;
`;
let ProfileImg = styled.img`
  border-radius: 10rem;
  background: black;
  width: 2rem;
  height: 2rem;
  float: left;
  font-size: 1.5rem;
  margin-right: 1rem;
`;
let Username = styled.div`
  font-weight: 1rem;
  font-size: 1.2rem;
  flex: 3;
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

const clickFollow = (username) => {
  axios
    .put("http://localhost:8080/users/mypage/" + username + "/follow")
    .then((res) => {
      console.log(res.data);
    })
    .catch((err) => {
      console.log(err);
    });
};

function Feedswrap(props) {
  const navigate = useNavigate();
  // const [commentIndex, setCommentIndex] = useState([]);
  const [like, setLike] = useState();
  const [viewLike, setViewLike] = useState(false);
  const [likeUser, setLikeUser] = useState();
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
        console.log(typeof res.data[0].id);
      })
      .catch((error) => {
        console.log(error);
      });
    data.map((a, b) => {
      axios
        .get("http://localhost:8080/feed/" + a.id + "/like")
        .then((res) => {
          console.log("좋아요불러오기");
          console.log(like);
          setLike([...like, res.data]);
        })
        .catch((err) => {
          console.log(err.data);
        });
    });
  }, [rerender]);

  const likeFeed = (id) => {
    axios
      .post("http://localhost:8080/feed/" + id + "/like")
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err.data);
      });
  };

  return (
    <Box
      sx={{
        width: "700px",
        margin: "auto",
      }}>
      {data.map(function (i, b) {
        return (
          <>
            <FeedWrap>
              <Paper
                elevation={3}
                style={{
                  borderRadius: "1rem",
                  padding: "0.8rem",
                  display: "flex",
                }}>
                <ProfileImg src={props.profileImg} />
                <Username>{i.user}</Username>
                <Time>
                  {i.date} {i.time}
                </Time>
                {i.user === props.username ? null : (
                  <Button
                    onClick={() => {
                      clickFollow(i.user);
                    }}>
                    팔로우
                  </Button>
                )}
              </Paper>
              <div style={{ marginTop: "0.4rem" }}>
                {i.fileUrl ? (
                  <div
                    style={{
                      background: "lightgrey",
                      margin: "auto",
                      borderRadius: "1rem",
                      marginTop: "0.8rem",
                      marginBottom: "0.8rem",
                    }}>
                    <img src={i.fileUrl} style={{ width: "100%" }} />
                  </div>
                ) : null}
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
                          if (viewLike) {
                            setViewLike(false);
                          } else if (likeUser === i.username) {
                            setViewLike(true);
                          }
                          likeFeed(i.id);
                        }}>
                        {viewLike ? <ThumbUpAltIcon /> : <ThumbUpOffAltIcon />}
                      </IconButton>
                      <IconButton>
                        <AddCommentIcon
                        // onClick={() => {
                        //   if (commentIndex.includes(b + 1)) {
                        //     setCommentIndex([
                        //       ...commentIndex.filter(
                        //         (item) => item !== b + 1
                        //       ),
                        //     ]);
                        //   } else {
                        //     console.log("테스트");
                        //     console.log(b + 1);
                        //     setCommentIndex([i.id, ...commentIndex]);
                        //     console.log(commentIndex);
                        //   }
                        // }}
                        />
                      </IconButton>
                      <IconButton>
                        <BookmarkAddIcon />
                      </IconButton>
                    </Icons>
                    <LikeText>{like}명이 좋아합니다.</LikeText>
                  </LCwrap>
                  <Title>{i.title}</Title>
                  <Contents>{i.content}</Contents>
                  <HashTag>
                    {i.hashtag.map((a, b) => {
                      return (
                        <span
                          style={{
                            color: "blue",
                            marginRight: "0.4rem",
                          }}>
                          {a.tagName}
                        </span>
                      );
                    })}
                  </HashTag>
                  {/* {commentIndex.includes(b + 1) ? ( */}
                  <>
                    <Comments
                      props={i.comments}
                      feed={i.id}
                      setRerender={setRerender}></Comments>
                  </>
                  {/* ) : null} */}
                </Paper>
              </BottomBox>
            </FeedWrap>
          </>
        );
      })}
    </Box>
  );
}
export default Feedswrap;
