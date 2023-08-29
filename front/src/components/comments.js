import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import React, { useState, useEffect } from "react";
import { Box, Stack, Paper, IconButton, TextField } from "@mui/material";
import axios from "axios";

import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import AddCommentIcon from "@mui/icons-material/AddComment";
import BookmarkAddIcon from "@mui/icons-material/BookmarkAdd";
import ThumbUpAltIcon from "@mui/icons-material/ThumbUpAlt";
import CloseIcon from "@mui/icons-material/Close";
import SendIcon from "@mui/icons-material/Send";

let CommentBox = styled.div``;

function Comments(commentData, parentData) {
  useEffect(() => {
    setFeedId(parentData.feed);
  }, []);
  console.log(commentData.props);
  const [data, setData] = useState(commentData.props);
  const [reply, setReply] = useState();
  const [feedId, setFeedId] = useState();
  const [commentId, setCommentId] = useState();
  const deleteComment = () => {
    axios
      .delete("http://localhost:8080/comment/" + feedId + "/" + commentId)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err.message);
      });
  };
  const replyHandler = (e) => {
    setReply(e.target.value);
  };
  const sendReply = () => {
    axios
      .post("http://localhost:8080/comment/" + feedId, reply)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <>
      <CommentBox>
        {data
          ? data.map(function (i, b) {
              return (
                <>
                  <Paper
                    elevation={3}
                    style={{
                      marginTop: "0.4rem",
                      borderRadius: "1rem",
                      padding: "0.8rem",
                    }}
                  >
                    <span
                      style={{
                        fontWeight: "bold",
                        marginRight: "0.8rem",
                      }}
                    >
                      {i.nickname}
                    </span>
                    {i.comment}
                    <IconButton
                      onClick={() => {
                        setCommentId(i.id);
                      }}
                    >
                      <CloseIcon onClick={deleteComment} />
                    </IconButton>
                  </Paper>
                </>
              );
            })
          : null}
        <div>
          <TextField
            fullwidth
            id="fullwidth"
            label="댓글달기"
            variant="standard"
            value={reply}
            onChange={replyHandler}
          />
          <IconButton>
            <SendIcon onClick={sendReply} />
          </IconButton>
        </div>
      </CommentBox>
    </>
  );
}
export default Comments;
