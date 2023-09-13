import "bootstrap/dist/css/bootstrap.min.css";

import { Routes, Route } from "react-router-dom";
import { useState } from "react";

import Login from "./route/login.js";
import Register from "./route/register.js";
import Mainpage from "./route/mainpage.js";
import Mypage from "./route/mypage.js";
import MypageSet from "./route/mypageset.js";
import Follower from "./route/follower.js";
import Scrap from "./route/scrap.js";
import ScrapView from "./route/scrapview.js";
import ChatList from "./route/chatlist.js";
import ChatPage from "./route/chatpage.js";
import ChatDelete from "./route/chatdelete.js";
import FeedDetail from "./route/feeddetail.js";
import CreateFeed from "./route/createfeed.js";
import UpdateFeed from "./route/updatefeed.js";
import SearchPage from "./route/searchpage.js";
import Following from "./route/following.js";

import Music from "./components/music.js";

function App() {
  const [username, setUsername] = useState();
  return (
    <>
      <div style={{ marginBottom: "10rem" }}>
        <Routes>
          <Route
            path="/"
            element={<Mainpage username={username} setUsername={setUsername} />}
          />
          <Route path="/login" element={<Login></Login>} />
          <Route path="/register" element={<Register></Register>} />
          {username ? (
            <>
              <Route
                path="/mypage"
                element={
                  <Mypage username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/mypageset"
                element={
                  <MypageSet username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/follower"
                element={
                  <Follower username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/following"
                element={
                  <Following username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/scrap"
                element={
                  <Scrap username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/scrapview"
                element={
                  <ScrapView username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/chatlist"
                element={
                  <ChatList username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/chatdelete"
                element={
                  <ChatDelete username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/chatpage/:id"
                element={
                  <ChatPage username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/feeddetail/:id"
                element={
                  <FeedDetail username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/createfeed"
                element={
                  <CreateFeed username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/updatefeed/:id"
                element={
                  <UpdateFeed username={username} setUsername={setUsername} />
                }
              />
              <Route
                path="/searchpage"
                element={
                  <SearchPage username={username} setUsername={setUsername} />
                }
              />
            </>
          ) : null}
        </Routes>
      </div>
      <Music />
    </>
  );
}

export default App;
