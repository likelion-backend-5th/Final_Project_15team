import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";

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
  Typography,
} from "@mui/material";

import Appbars from "../components/appbars";

let Title = styled.div`
  font-size: 2rem;
  text-align: center;
  padding: 1.2rem;
`;

function Following() {
  let [follow, setFollow] = useState([]);
  let [follower, setFollower] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/sns").then((res) => {
      console.log(res.data);
      setFollow(res.data);
      setFollower(res.data);
    });
  }, []);
  return (
    <>
      <Appbars></Appbars>
      <Box style={{ display: "flex" }}>
        <Paper
          elevation={3}
          style={{ margin:"0.8rem", padding: "0.8rem", width: "100%", maxWidth:"50rem" }}>
          <Title>팔로잉</Title>
          <List
           sx={{ width: "100%", bgcolor: "background.paper" }}>
            {follower.map(function (i, b) {
              return (
                <>
                  <ListItem alignItems="flex-start">
                    <ListItemAvatar>
                      <Avatar
                        alt="Remy Sharp"
                        src="/static/images/avatar/1.jpg"
                      />
                    </ListItemAvatar>
                    <ListItemText primary={i.nickname} />
                  </ListItem>
                  <Divider variant="inset" component="li" style={{margin:'0.8rem'}} />
                </>
              );
            })}
          </List>
        </Paper>
      </Box>
    </>
  );
}
export default Following;
