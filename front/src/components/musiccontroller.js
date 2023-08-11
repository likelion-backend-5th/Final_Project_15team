import * as React from "react";
import { useTheme } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import SkipPreviousIcon from "@mui/icons-material/SkipPrevious";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import SkipNextIcon from "@mui/icons-material/SkipNext";

export default function Musiccontroller() {
  const theme = useTheme();

  return (
    <Card sx={{ display: "flex", background: "transparent", color: "white" }}>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
        }}>
        <CardContent sx={{ flex: "1 0 auto", textAlign: "center" }}>
          <Typography component="div" variant="h5">
            좋은날
          </Typography>
          <Typography
            variant="subtitle1"
            color="text.secondary"
            component="div"
            sx={{
              color: "white",
            }}>
            아이유
          </Typography>
        </CardContent>
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            pl: 1,
            pb: 1,
          }}>
          <IconButton
            aria-label="previous"
            sx={{
              color: "white",
            }}>
            {theme.direction === "rtl" ? (
              <SkipNextIcon />
            ) : (
              <SkipPreviousIcon />
            )}
          </IconButton>
          <IconButton aria-label="play/pause">
            <PlayArrowIcon sx={{ height: 38, width: 38, color: "white" }} />
          </IconButton>
          <IconButton
            aria-label="next"
            sx={{
              color: "white",
            }}>
            {theme.direction === "rtl" ? (
              <SkipPreviousIcon />
            ) : (
              <SkipNextIcon />
            )}
          </IconButton>
        </Box>
      </Box>
      <CardMedia
        component="img"
        sx={{ width: 151, background: "black" }}
        image="../static/cover.jpg"
        alt="앨범커버"
      />
    </Card>
  );
}
