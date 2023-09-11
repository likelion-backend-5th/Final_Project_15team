import React, { useEffect, useState } from "react";
import {
  AppBar,
  Box,
  Toolbar,
  Typography,
  IconButton,
  MenuItem,
  Menu,
  Button,
} from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import AccountCircle from "@mui/icons-material/AccountCircle";
import ChatIcon from "@mui/icons-material/Chat";
import AddIcon from "@mui/icons-material/Add";

import { useNavigate } from "react-router-dom";

function Appbars(props) {
  const [auth, setAuth] = useState(true);
  const [anchorEl, setAnchorEl] = useState(null);

  useEffect(() => {
    if (props.username) {
      setAuth(true);
    } else {
      setAuth(false);
    }
  }, [props.username]);

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  const handleLogout = () => {
    navigate("/");
    window.location.reload();
  };

  let navigate = useNavigate();
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" sx={{ background: "#5B61A1" }}>
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={() => {
              navigate("/");
            }}>
            <HomeIcon></HomeIcon>
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            SNS Music
          </Typography>
          {auth && (
            <div>
              <IconButton
                size="large"
                onClick={() => {
                  navigate("/createfeed");
                }}>
                <AddIcon style={{ color: "white" }} />
              </IconButton>
              <IconButton
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleMenu}
                color="inherit">
                <AccountCircle />
              </IconButton>
              <IconButton
                size="large"
                onClick={() => {
                  navigate("/chatlist");
                }}
                color="inherit">
                <ChatIcon />
              </IconButton>
              <Menu
                id="menu-appbar"
                anchorEl={anchorEl}
                anchorOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                open={Boolean(anchorEl)}
                onClose={handleClose}>
                <MenuItem
                  onClick={() => {
                    navigate("/mypage");
                  }}>
                  Profile
                </MenuItem>
                <MenuItem onClick={handleClose}>My account</MenuItem>
                <MenuItem onClick={handleLogout}>Logout</MenuItem>
              </Menu>
            </div>
          )}
          {auth ? null : (
            <div>
              <Button
                variant="contained"
                sx={{ background: "white", color: "blue" }}
                onClick={() => {
                  navigate("/login");
                }}>
                로그인
              </Button>
            </div>
          )}
        </Toolbar>
      </AppBar>
    </Box>
  );
}

export default Appbars;
