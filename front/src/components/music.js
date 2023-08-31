import React, { useState, useEffect } from "react";
import YouTube from "react-youtube";
import axios from "axios";

import MusicSearch from "./musicsearch";

import { Box, Card, CardContent, CardMedia, IconButton } from "@mui/material";
import SkipPreviousIcon from "@mui/icons-material/SkipPrevious";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import PauseIcon from "@mui/icons-material/Pause";
import SkipNextIcon from "@mui/icons-material/SkipNext";

function Music() {
  // const [data, setData] = useState([]);

  //   const [player, setPlayer] = useState(null);
  //   const [videoTitle, setVideoTitle] = useState("");
  //   const [progress, setProgress] = useState(0);
  //   const [currentTime, setCurrentTime] = useState(0);
  //   const [totalTime, setTotalTime] = useState(0);
  //   const [isPlaying, setIsPlaying] = useState(false);
  //   const [volume, setVolume] = useState(50);
  const [videoId, setVideoId] = useState();
  const [coverImg, setCoverImg] = useState();
  // const [onReady, setOnReady] = useState();
  const [onStateChange, setOnStateChange] = useState();
  const [videoTitle, setVideoTitle] = useState();
  const [isPlaying, setIsPlaying] = useState(false);

  // const _onReady = (event) => {
  //   console.log(event);
  //   // access to player in all event handlers via event.
  //   if (event.data === 2 || null) {
  //     event.target.playVideo();
  //   } else if (event.data === 1) {
  //     event.target.pauseVideo();
  //   }
  // };
  const _onStateChange = (event) => {
    console.log(event);
    if (event.data === 2 || null) {
      event.target.playVideo();
      setIsPlaying(true);
    } else if (event.data === 1) {
      event.target.pauseVideo();
      setIsPlaying(false);
    }
  };

  const opts = {
    height: "0",
    width: "0",
    playerVars: { autoplay: 1 },
  };
  // useEffect(() => {
  //   let tempVideoId = "";
  //   axios
  //     .get("http://localhost:8080/feed")
  //     .then((res) => {
  //       console.log(res.data);
  //       setData(res.data);
  //       tempVideoId = res.data[0].videoId;
  //       let [a, b] = tempVideoId.split("=");
  //       if (b) {
  //         setVideoId(b);
  //       }
  //       setCoverImg(res.data[0].imageUrlPath);
  //     })
  //     .then(() => {})
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }, []);
  return (
    <>
      <YouTube
        videoId={videoId}
        opts={opts}
        onReady={(e) => {
          // setOnReady(e);
          setVideoTitle(e.target.videoTitle);
          console.log(e.target.videoTitle);
          setIsPlaying(true);
          //   e.target.mute();
          //   e.target.pauseVideo();
        }}
        onStateChange={(e) => {
          setOnStateChange(e);
        }}
      />
      <Card
        sx={{
          display: "flex",
          background: "transparent",
          color: "white",
          backgroundColor: "#003a88",
          borderRadius: "1rem",
          padding: "0.8rem",
          margin: "0.8rem",
          width: 500,
        }}
      >
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            margin: "auto",
          }}
        >
          <CardContent sx={{ flex: "1 0 auto", textAlign: "center" }}>
            <div style={{ maxWidth: 200 }}>{videoTitle}</div>
          </CardContent>
          {/* <div style={{ maxWidth: 200, margin: "auto" }}>
            <input
              type="range"
              value={progress}
              onChange={(e) => seekToTime(e.target.value)}
              style={{ margin: "auto", width: 200 }}
            />
            <span>{formatTime(currentTime)}</span> /
            <span>{formatTime(totalTime)}</span>
          </div>
          <div style={{ maxWidth: 200, margin: "auto" }}>
            <input
              type="range"
              value={volume}
              onChange={(e) => changeVolume(e.target.value)}
              min="0"
              max="100"
              style={{ margin: "auto", width: 200 }}
            />
          </div> */}
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              pl: 1,
              pb: 1,
            }}
            style={{ margin: "auto" }}
          >
            <IconButton sx={{ color: "white" }}>
              <SkipPreviousIcon />
            </IconButton>
            <IconButton
              sx={{ color: "white" }}
              onClick={() => _onStateChange(onStateChange)}
            >
              {isPlaying ? <PauseIcon /> : <PlayArrowIcon />}
            </IconButton>
            <IconButton sx={{ color: "white" }}>
              <SkipNextIcon />
            </IconButton>
          </Box>
        </Box>
        <CardMedia
          component="img"
          sx={{
            width: 210,
            height: 210,
            background: "black",
            borderRadius: "1rem",
            margin: "auto",
          }}
          image={coverImg ? coverImg : null}
          alt="앨범커버"
        />
      </Card>
      {/* <button onClick={() => _onReady(onReady)}>재생</button>
      <button onClick={() => _onStateChange(onStateChange)}>상태</button> */}
      <MusicSearch setVideoId={setVideoId} setCoverImg={setCoverImg} />
    </>
  );
}

export default Music;
