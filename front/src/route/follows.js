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

import HomeIcon from "@mui/icons-material/Home";
import SettingsIcon from "@mui/icons-material/Settings";

let Title = styled.div`
  font-size: 2rem;
  text-align: center;
  padding: 1.2rem;
`;

function Follows() {
  let [follow, setFollow] = useState([]);
  let [follower, setFollower] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:3000/sns").then((res) => {
      console.log(res.data);
      setFollow(res.data);
      setFollower(res.data);
    });
  }, []);
  return (
    <>
      <Box style={{ display: "flex" }}>
        <Paper
          elevation={3}
          style={{ width: "50%", margin: "1.2rem", marginRight: "0.4rem" }}>
          <Title>팔로워</Title>
          <List
            sx={{ width: "100%", maxWidth: 360, bgcolor: "background.paper" }}>
            {follow.map(function (i, b) {
              return (
                <>
                  <ListItem alignItems="flex-start">
                    <ListItemAvatar>
                      <Avatar
                        alt="Remy Sharp"
                        src="/static/images/avatar/1.jpg"
                      />
                    </ListItemAvatar>
                    <ListItemText
                      primary={i.title}
                      secondary={
                        <React.Fragment>
                          <Typography
                            sx={{ display: "inline" }}
                            component="span"
                            variant="body2"
                            color="text.primary">
                            i.content
                          </Typography>
                        </React.Fragment>
                      }
                    />
                  </ListItem>
                  <Divider variant="inset" component="li" />
                </>
              );
            })}
          </List>
        </Paper>
        <Paper
          elevation={3}
          style={{ width: "50%", margin: "1rem", marginLeft: "0.4rem" }}>
          <Title>팔로잉</Title>
          <List
            sx={{ width: "100%", maxWidth: 360, bgcolor: "background.paper" }}>
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
                    <ListItemText
                      primary={i.title}
                      secondary={
                        <React.Fragment>
                          <Typography
                            sx={{ display: "inline" }}
                            component="span"
                            variant="body2"
                            color="text.primary">
                            i.content
                          </Typography>
                        </React.Fragment>
                      }
                    />
                  </ListItem>
                  <Divider variant="inset" component="li" />
                </>
              );
            })}
          </List>
        </Paper>
      </Box>
    </>
  );
}
export default Follows;
