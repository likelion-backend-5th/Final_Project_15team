import React, { useState, useEffect } from "react";
import YouTube from "react-youtube";
import axios from "axios";

function Music() {
  const [data, setData] = useState([]);

  //   const [player, setPlayer] = useState(null);
  //   const [videoTitle, setVideoTitle] = useState("");
  //   const [progress, setProgress] = useState(0);
  //   const [currentTime, setCurrentTime] = useState(0);
  //   const [totalTime, setTotalTime] = useState(0);
  //   const [isPlaying, setIsPlaying] = useState(false);
  //   const [volume, setVolume] = useState(50);
  const [videoId, setVideoId] = useState();
  const [coverImg, setCoverImg] = useState();

  const _onReady = (event) => {
    // access to player in all event handlers via event.target
    event.target.pauseVideo();
  };
  const opts = {
    height: "390",
    width: "640",
    playerVars: {},
  };
  useEffect(() => {
    let tempVideoId = "";
    axios
      .get("http://localhost:8080/feed")
      .then((res) => {
        console.log(res.data);
        setData(res.data);
        tempVideoId = res.data[0].videoId;
        let [a, b] = tempVideoId.split("=");
        if (b) {
          setVideoId(b);
        }
        setCoverImg(res.data[0].imageUrlPath);
      })
      .then(() => {})
      .catch((error) => {
        console.log(error);
      });
  }, []);
  return (
    <>
      <YouTube
        videoId={videoId}
        opts={opts}
        onReady={(e) => {
          //   e.target.mute();
          //   e.target.pauseVideo();
        }}
      />
      <button onClick={_onReady}>재생</button>{" "}
    </>
  );
}

export default Music;
