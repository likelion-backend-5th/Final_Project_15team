import * as React from "react";
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
  Typography,
  TextField
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

export default function Searchbox() {
  return (
    
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
            </Paper>
    
  );
}
