import React, { useState, useEffect } from "react";

import { useTheme } from "@mui/material/styles";
import {
  Box,
  Card,
  CardContent,
  CardMedia,
  IconButton,
  Typography,
} from "@mui/material";
import SkipPreviousIcon from "@mui/icons-material/SkipPrevious";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import SkipNextIcon from "@mui/icons-material/SkipNext";
import PauseIcon from '@mui/icons-material/Pause';

function MusicPlayer() {
  const [player, setPlayer] = useState(null);
  const [videoTitle, setVideoTitle] = useState("");
  const [progress, setProgress] = useState(0);
  const [currentTime, setCurrentTime] = useState(0);
  const [totalTime, setTotalTime] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);
  const [volume, setVolume] = useState(50);

  useEffect(() => {
    const tag = document.createElement("script");
    tag.src = "https://www.youtube.com/iframe_api";
    const firstScriptTag = document.getElementsByTagName("script")[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    window.onYouTubeIframeAPIReady = initializePlayer;

    return () => {
      delete window.onYouTubeIframeAPIReady;
    };
  }, []);

  const initializePlayer = () => {
    setPlayer(
      new window.YT.Player("player", {
        height: "0",
        width: "0",
        videoId: "JGwWNGJdvx8", //music id, 일단 임의로 지정해둠
        playerVars: {
          rel: 0,
          controls: 0,
          autoplay: 1,
          loop: 1,
          playsinline: 1,
        },
        events: {
          onReady: onPlayerReady,
          onStateChange: onPlayerStateChange,
        },
      })
    );
  };

  const onPlayerReady = (event) => {
    setVideoTitle(event.target.getVideoData().title);
    setTotalTime(event.target.getDuration());
    setInterval(updateProgressBar, 1000);
  };

  const onPlayerStateChange = (event) => {
    if (event.data === window.YT.PlayerState.PLAYING) {
      setIsPlaying(true);
    } else {
      setIsPlaying(false);
    }
  };

  const updateProgressBar = () => {
    if(player){
    const currentTime = player.getCurrentTime();
    const duration = player.getDuration();
    setProgress((currentTime / duration) * 100);
    setCurrentTime(currentTime);
    }
  };

  const formatTime = (time) => {
    const minutes = Math.floor(time / 60);
    const seconds = Math.floor(time % 60);
    return `${minutes}:${seconds < 10 ? "0" : ""}${seconds}`;
  };

  const togglePlayPause = () => {
    if (isPlaying) {
      player.pauseVideo();
    } else {
      player.playVideo();
    }
  };

  const seekToTime = (value) => {
    const duration = player.getDuration();
    const seekTime = (value / 100) * duration;
    player.seekTo(seekTime);
  };

  const changeVolume = (newVolume) => {
    player.setVolume(newVolume);
    setVolume(newVolume);
  };

  return (
    <>
    <div id="player"></div>
    <Card sx={{ display: "flex", background: "transparent", color: "white" }}>
    <Box
        sx={{
          display: "flex",
          flexDirection: "column",
        }}>
            <CardContent sx={{ flex: "1 0 auto", textAlign: "center" }}>
          <Typography component="div" variant="h5">
            {videoTitle}
          </Typography>
          </CardContent>
          <button onClick={togglePlayPause}>
        {isPlaying ? "일시정지" : "재생"}
      </button>
      <input
        type="range"
        value={progress}
        onChange={(e) => seekToTime(e.target.value)}
      />
      <span>{formatTime(currentTime)}</span> /{" "}
      <span>{formatTime(totalTime)}</span>
      <input
        type="range"
        value={volume}
        onChange={(e) => changeVolume(e.target.value)}
        min="0"
        max="100"
      />
        </Box>
    </Card>
    <div>
      <div id="player"></div>
      <h2 id="videoTitle">{videoTitle}</h2>
      <button onClick={togglePlayPause}>
        {isPlaying ? "일시정지" : "재생"}
      </button>
      <input
        type="range"
        value={progress}
        onChange={(e) => seekToTime(e.target.value)}
      />
      <span>{formatTime(currentTime)}</span> /{" "}
      <span>{formatTime(totalTime)}</span>
      <input
        type="range"
        value={volume}
        onChange={(e) => changeVolume(e.target.value)}
        min="0"
        max="100"
      />
    </div>
    </>

  );
}

export default MusicPlayer;
