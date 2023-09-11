import React, { useState } from "react";
import YouTube from "react-youtube";

import MusicSearch from "./musicsearch";

import { Box, Card, CardContent, CardMedia, IconButton } from "@mui/material";
import SkipPreviousIcon from "@mui/icons-material/SkipPrevious";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import PauseIcon from "@mui/icons-material/Pause";
import SkipNextIcon from "@mui/icons-material/SkipNext";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import VolumeOffIcon from "@mui/icons-material/VolumeOff";
import AllInclusiveIcon from "@mui/icons-material/AllInclusive";

function Music() {
  const [videoId, setVideoId] = useState();
  const [coverImg, setCoverImg] = useState();
  const [onStateChange, setOnStateChange] = useState();
  const [videoTitle, setVideoTitle] = useState();
  const [isPlaying, setIsPlaying] = useState(false);
  const [volume, setVolume] = useState();
  const [volumeControl, setVolumeControl] = useState();
  const [isMuted, setIsMuted] = useState(false);
  const [mute, setMute] = useState();
  const [playLoop, setPlayLoop] = useState();
  const [loop, setLoop] = useState(false);
  const [previous, setPrevious] = useState();
  const [next, setNext] = useState();
  const [modal, setModal] = useState();

  const _onEnd = (event) => {
    // 음악이 종료되면 반복 재생 상태를 확인하고 다시 재생
    if (loop) {
      event.target.playVideo();
    } else {
      // 반복 재생 상태가 아니면 다음 비디오를 재생하거나 종료
      _playNext(next);
    }
  };

  const _playNext = (player) => {
    player.nextVideo();
  };
  const _playPrevious = (player) => {
    console.log("이전영상 재생");
    player.previousVideo();
  };

  const _playLoop = (player) => {
    if (loop) {
      player.setLoop(false);
      setLoop(false);
    } else {
      player.setLoop(true);
      setLoop(true);
    }
  };

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

  const _controlMute = (player) => {
    console.log(player);
    if (isMuted) {
      setIsMuted(false);
      player.unMute();
    } else if (!isMuted) {
      setIsMuted(true);
      player.mute();
    }
  };

  const _controlVolume = (setVolumeControl, volume) => {
    console.log(setVolumeControl.setVolume);
    setVolumeControl.setVolume(volume);
    setVolume(volume);
  };
  const opts = {
    height: "0",
    width: "0",
    playerVars: { autoplay: 1 },
  };

  const searchModal = () => {
    if (modal) {
      setModal(false);
    } else {
      setModal(true);
    }
  };

  return (
    <>
      <YouTube
        videoId={videoId}
        opts={opts}
        onReady={(e) => {
          setVideoTitle(e.target.videoTitle);
          console.log(e.target);
          setIsPlaying(true);
          setVolume(e.target.playerInfo.volume);
          setIsMuted(e.target.playerInfo.muted);
          setMute(e.target);
          setPlayLoop(e.target);
          setPrevious(e.target);
          setNext(e.target);
        }}
        onStateChange={(e) => {
          setOnStateChange(e);
          setVolumeControl(e.target);
        }}
        onEnd={(e) => {
          _onEnd(e);
        }}
      />
      <Card
        sx={{
          display: "flex",
          background: "transparent",
          color: "white",
          backgroundColor: "#6C85BD",
          borderRadius: "1rem",
          padding: "0.8rem",
          margin: "auto",
          marginBottom: "0.4rem",
          width: 1300,
        }}>
        <button onClick={searchModal}>검색</button>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            margin: "auto",
          }}>
          <CardContent sx={{ flex: "1 0 auto", textAlign: "center" }}>
            <div style={{ maxWidth: 200 }}>{videoTitle}</div>
          </CardContent>

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              pl: 1,
              pb: 1,
            }}
            style={{ margin: "auto" }}>
            <IconButton sx={{ color: "white" }}>
              <SkipPreviousIcon
                onClick={() => {
                  _playPrevious(previous);
                }}
              />
            </IconButton>
            <IconButton
              sx={{ color: "white" }}
              onClick={() => _onStateChange(onStateChange)}>
              {isPlaying ? <PauseIcon /> : <PlayArrowIcon />}
            </IconButton>
            <IconButton sx={{ color: "white" }}>
              <SkipNextIcon
                onClick={() => {
                  _playNext(next);
                }}
              />
            </IconButton>
            <IconButton
              onClick={() => {
                _playLoop(playLoop);
              }}>
              {loop ? (
                <AllInclusiveIcon sx={{ color: "white" }} />
              ) : (
                <AllInclusiveIcon sx={{ color: " #AED3E3" }} />
              )}
            </IconButton>
          </Box>
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              pl: 1,
              pb: 1,
            }}
            style={{ margin: "auto" }}>
            {" "}
            <IconButton
              sx={{ color: "white" }}
              onClick={() => _controlMute(mute)}>
              {isMuted ? <VolumeOffIcon /> : <VolumeUpIcon />}
            </IconButton>
            <div style={{ maxWidth: 200, margin: "auto" }}>
              <input
                type="range"
                value={volume}
                onChange={(e) => _controlVolume(volumeControl, e.target.value)}
                min="0"
                max="100"
                style={{ margin: "auto", width: 200 }}
              />
            </div>
          </Box>
        </Box>
        <CardMedia
          component="img"
          sx={{
            width: 150,
            height: 150,
            background: "black",
            borderRadius: "1rem",
            margin: "auto",
          }}
          image={coverImg ? coverImg : null}
          alt=""
        />
      </Card>
      {modal ? (
        <div>
          <MusicSearch setVideoId={setVideoId} setCoverImg={setCoverImg} />
        </div>
      ) : null}
    </>
  );
}

export default Music;
