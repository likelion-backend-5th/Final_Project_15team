import * as React from "react";
import {
  Switch,
  AppBar,
  Box,
  Toolbar,
  Typography,
  IconButton,
  FormGroup,
  MenuItem,
  Menu,
  Button,
  FormControlLabel,
} from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import MenuIcon from "@mui/icons-material/Menu";
import AccountCircle from "@mui/icons-material/AccountCircle";
import ChatIcon from "@mui/icons-material/Chat";

import { useNavigate } from "react-router-dom";

export default function Appbars() {
  const [auth, setAuth] = React.useState(true);
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleChange = (event) => {
    setAuth(event.target.checked);
  };

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  let navigate = useNavigate();
  return (
    <Box sx={{ flexGrow: 1 }}>
      <FormGroup>
        <FormControlLabel
          control={
            <Switch
              checked={auth}
              onChange={handleChange}
              aria-label="login switch"
            />
          }
          label={auth ? "Logout" : "Login"}
        />
      </FormGroup>
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
                <MenuItem onClick={handleClose}>Logout</MenuItem>
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
                Login
              </Button>
            </div>
          )}
        </Toolbar>
      </AppBar>
    </Box>
  );
}
