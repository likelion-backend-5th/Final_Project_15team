import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import React, { useState, useEffect } from "react";
import { Paper, IconButton, TextField } from "@mui/material";
import axios from "axios";

import CloseIcon from "@mui/icons-material/Close";
import SendIcon from "@mui/icons-material/Send";
import CreateIcon from "@mui/icons-material/Create";

let CommentBox = styled.div``;

function Comments(commentData) {
  const [data, setData] = useState(commentData.props);
  const [reply, setReply] = useState();
  const [feedId, setFeedId] = useState();
  const [commentId, setCommentId] = useState();
  const [putComment, setPutComment] = useState();
  const [viewPutComment, setViewPutComment] = useState(false);

  useEffect(() => {
    setFeedId(commentData.id);
  }, []);
  const updateComment = () => {
    const formData = new FormData();
    formData.append("content", putComment);
    axios
      .put(
        "http://localhost:8080/comment/" + feedId + "/" + commentId,
        formData
      )
      .then((res) => {
        console.log(res);
        commentData.setRerender(Math.random());
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const putCommentHandler = (e) => {
    setPutComment(e.target.value);
  };

  const deleteComment = () => {
    axios
      .delete("http://localhost:8080/comment/" + feedId + "/" + commentId)
      .then((res) => {
        console.log(res);
        commentData.setRerender(Math.random());
      })
      .catch((err) => {
        console.log(err.message);
      });
  };
  const replyHandler = (e) => {
    setReply(e.target.value);
  };
  const sendReply = () => {
    const formData = new FormData();
    formData.append("content", reply);
    axios
      .post("http://localhost:8080/comment/" + feedId, formData)
      .then((res) => {
        console.log("댓글 생성");
        console.log(res);
        commentData.setRerender(Math.random());
        setReply("");
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
                  <div
                    key={b}
                    elevation={3}
                    style={{
                      marginTop: "0.2rem",
                      borderRadius: "1rem",
                      display: "flex",
                      fontSize: "1.2rem",
                      paddingLeft: "0.8rem",
                    }}>
                    <span
                      style={{
                        fontWeight: "bold",
                        marginRight: "0.8rem",
                      }}>
                      {i.nickname}
                    </span>
                    <div style={{ flex: "2" }}>{i.content}</div>
                    <IconButton>
                      <CreateIcon
                        onClick={() => {
                          if (!viewPutComment) {
                            setViewPutComment(true);
                          } else if (viewPutComment) {
                            setViewPutComment(false);
                          }
                        }}
                      />
                    </IconButton>
                    {viewPutComment ? (
                      <div>
                        {" "}
                        <TextField
                          fullwidth
                          id="fullwidth"
                          label="댓글수정"
                          variant="standard"
                          value={putComment}
                          onChange={putCommentHandler}
                        />
                        <IconButton
                          onClick={() => {
                            setCommentId(i.id);
                          }}>
                          <SendIcon onClick={updateComment} />
                        </IconButton>
                      </div>
                    ) : null}
                    <IconButton
                      onClick={() => {
                        setCommentId(i.id);
                      }}>
                      <CloseIcon onClick={deleteComment} />
                    </IconButton>
                  </div>
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
