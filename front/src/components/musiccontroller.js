import React,{useEffect, useState} from "react";
import axios from 'axios';
import { useTheme } from "@mui/material/styles";
import {
  Box,
  Card,
  CardContent,
  CardMedia,
  IconButton,
} from "@mui/material";
import SkipPreviousIcon from "@mui/icons-material/SkipPrevious";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import PauseIcon from '@mui/icons-material/Pause';
import SkipNextIcon from "@mui/icons-material/SkipNext";

export default function Musiccontroller() {

  const [data,setData] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/feed").then((res) => {
      console.log(res.data);
      setData(res.data);
    });

    const tag = document.createElement("script");
    tag.src = "https://www.youtube.com/iframe_api";
    const firstScriptTag = document.getElementsByTagName("script")[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    window.onYouTubeIframeAPIReady = initializePlayer;

    return () => {
      delete window.onYouTubeIframeAPIReady;
    };
  },[]);

  const theme = useTheme();

  const [player, setPlayer] = useState(null);
  const [videoTitle, setVideoTitle] = useState("");
  const [progress, setProgress] = useState(0);
  const [currentTime, setCurrentTime] = useState(0);
  const [totalTime, setTotalTime] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);
  const [volume, setVolume] = useState(50);

  // useEffect(() => {
  //   const tag = document.createElement("script");
  //   tag.src = "https://www.youtube.com/iframe_api";
  //   const firstScriptTag = document.getElementsByTagName("script")[0];
  //   firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

  //   window.onYouTubeIframeAPIReady = initializePlayer;

  //   return () => {
  //     delete window.onYouTubeIframeAPIReady;
  //   };
  // }, []);

  const initializePlayer = () => {
    setPlayer(
      new window.YT.Player("player", {
        height: "0",
        width: "0",
        // videoId: musicId,
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
          <div style={{maxWidth:150}}>{videoTitle}</div>
        </CardContent>
        <div style={{width: 150}}>
         <input
        type="range"
        value={progress}
        onChange={(e) => seekToTime(e.target.value)}
      />
      
      <span>{formatTime(currentTime)}</span> /
      <span>{formatTime(totalTime)}</span>
      </div>
      <div style={{width: 150}}>
      <input
        type="range"
        value={volume}
        onChange={(e) => changeVolume(e.target.value)}
        min="0"
        max="100"
      />
      </div>
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
          <IconButton aria-label="play/pause" onClick={togglePlayPause}>
            {isPlaying?<PauseIcon sx={{ height: 38, width: 38, color: "white" }} />: <PlayArrowIcon sx={{ height: 38, width: 38, color: "white" }} />}
            
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
        sx={{ width:150, height:150, background: "black" }}
        image={data[0] ? data[0].imageUrl : null}
        alt="앨범커버"
      />
    </Card>
    </>
  );
}
