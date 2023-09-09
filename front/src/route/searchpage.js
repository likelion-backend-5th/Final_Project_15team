import React from "react";

import { styled } from "@mui/material/styles";

import {
  Paper,
  InputBase,
  Divider,
  IconButton,
  Box,
  List,
  ListItem,
  ListItemText,
  Grid,
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

import Appbars from "../components/appbars";

function generate(element) {
  return [0, 1, 2].map((value) =>
    React.cloneElement(element, {
      key: value,
    })
  );
}

const Demo = styled("div")(({ theme }) => ({
  backgroundColor: theme.palette.background.paper,
}));

function SearchPage(props) {
  const [dense] = React.useState(false);
  const [secondary] = React.useState(false);
  return (
    <>
      <Appbars
        username={props.username}
        setUsername={props.setUsername}></Appbars>
      <Box style={{ display: "flex" }}>
        <Paper
          elevation={3}
          style={{
            width: "50%",
            margin: "1.2rem",
            marginRight: "0.4rem",
          }}>
          <Paper
            component="form"
            sx={{
              p: "2px 4px",
              display: "flex",
              alignItems: "center",
              width: 400,
              margin: "1rem",
            }}>
            <InputBase
              sx={{ ml: 1, flex: 1 }}
              placeholder="검색어를 입력해주세요"
              inputProps={{ "aria-label": "search google maps" }}
            />
            <IconButton type="button" sx={{ p: "10px" }} aria-label="search">
              <SearchIcon />
            </IconButton>
            <Divider sx={{ height: 28, m: 0.5 }} orientation="vertical" />
          </Paper>
          <Paper
            sx={{
              p: "2px 4px",
              display: "flex",
              alignItems: "center",
              width: 400,
              margin: "1rem",
            }}>
            <Grid item xs={12} md={6}>
              <Demo>
                <List dense={dense}>
                  {generate(
                    <ListItem>
                      <ListItemText
                        primary="Single-line item"
                        secondary={secondary ? "Secondary text" : null}
                      />
                    </ListItem>
                  )}
                </List>
              </Demo>
            </Grid>
          </Paper>
        </Paper>
      </Box>
    </>
  );
}
export default SearchPage;
