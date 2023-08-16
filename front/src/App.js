import "bootstrap/dist/css/bootstrap.min.css";

import { Routes, Route } from "react-router-dom";

import Login from "./route/login.js";
import Register from "./route/register.js";
import Mainpage from "./route/mainpage.js";
import Mypage from "./route/mypage.js";
import MypageSet from "./route/mypageset.js";
import Follows from "./route/follows.js";
import Scrap from "./route/scrap.js";
import ScrapView from "./route/scrapview.js";
import ChatList from "./route/chatlist.js";
import ChatPage from "./route/chatpage.js";
import ChatDelete from "./route/chatdelete.js";
import FeedDetail from "./route/feeddetail.js";
import CreateFeed from "./route/createfeed.js";
import UpdateFeed from "./route/updatefeed.js";
import SearchPage from "./route/searchpage.js";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Mainpage />} />
        <Route path="/login" element={<Login></Login>} />
        <Route path="/register" element={<Register></Register>} />
        <Route path="/mypage" element={<Mypage></Mypage>} />
        <Route path="/mypageset" element={<MypageSet></MypageSet>} />
        <Route path="/follows" element={<Follows></Follows>} />
        <Route path="/scrap" element={<Scrap></Scrap>} />
        <Route path="/scrapview" element={<ScrapView></ScrapView>} />
        <Route path="/chatlist" element={<ChatList></ChatList>} />
        <Route path="/chatdelete" element={<ChatDelete></ChatDelete>} />
        <Route path="/chatpage" element={<ChatPage></ChatPage>} />
        <Route path="/feeddetail" element={<FeedDetail></FeedDetail>} />
        <Route path="/createfeed" element={<CreateFeed></CreateFeed>} />
        <Route path="/updatefeed" element={<UpdateFeed></UpdateFeed>} />
        <Route path="/searchpage" element={<SearchPage></SearchPage>} />
      </Routes>
    </>
  );
}

export default App;
