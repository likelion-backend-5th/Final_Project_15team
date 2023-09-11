import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";
import axios from "axios";

import React, { useEffect, useState } from "react";
import {
  Box,
  Paper,
  List,
  ListItem,
  ListItemText,
  Divider,
} from "@mui/material";

import Appbars from "../components/appbars";

let Title = styled.div`
  font-size: 1.6rem;
  text-align: center;
  padding: 1.2rem;
`;

function Scrap(props) {
  const [data, setData] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:8080/feed")
      .then((res) => {
        console.log(res.data);
        setData(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  return (
    <>
      <Appbars
        username={props.username}
        setUsername={props.setUsername}></Appbars>
      <Box style={{ display: "flex", marginTop: "1.2rem" }}>
        <Paper
          elevation={3}
          style={{
            margin: "auto",
            padding: "0.8rem",
            width: "100%",
            maxWidth: "50rem",
          }}>
          <Title>스크랩</Title>
          <List sx={{ width: "100%", bgcolor: "background.paper" }}>
            {data.map(function (i, b) {
              return (
                <>
                  <ListItem
                    alignItems="flex-start"
                    style={{ textAlign: "center" }}>
                    <ListItemText primary={i.title} />
                  </ListItem>
                  <Divider
                    variant="inset"
                    component="li"
                    style={{ margin: "0.8rem" }}
                  />
                </>
              );
            })}
          </List>
        </Paper>
      </Box>
    </>
  );
}

export default Scrap;
