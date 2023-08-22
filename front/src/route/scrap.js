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

function Scrap() {
  const [data, setData] = useState([]);
  useEffect(() => {
    axios.get("http://localhost:3000/sns").then((res) => {
      console.log(res.data);
      setData(res.data);
    });
  }, []);
  return (
    <>
      <Appbars></Appbars>
      <Box style={{ display: "flex" }}>
        <Paper
          elevation={3}
          style={{ width: "50%", margin: "1.2rem", marginRight: "0.4rem" }}>
          <Title>스크랩</Title>
          <List
            sx={{ width: "100%", maxWidth: 360, bgcolor: "background.paper" }}>
            {data.map(function (i, b) {
              return (
                <>
                  <ListItem alignItems="flex-start">
                    <ListItemText primary={i.title} />
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

export default Scrap;
