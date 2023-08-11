import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import AddCommentIcon from "@mui/icons-material/AddComment";

let FeedWrap = styled.div`
  // background: #00457e;
  background: #dfe9f5;
  margin: 2rem;
  border-radius: 1rem;
  padding: 2rem;
  box-shadow: 0.2rem 0.2rem 1rem black;
  // color: white;
  color: black;
`;
let Idwrap = styled.div`
  padding: 0.5rem;
  background: white;
  border-radius: 1rem;
`;

let LCwrap = styled.div`
  padding: 0.5rem;
`;

let HashTag = styled.div`
  padding: 0.5rem;
`;
let Title = styled.div`
  padding: 0.5rem;
  font-size: 1.5rem;
`;
let ProfileImg = styled.div`
  border-radius: 10rem;
  background: black;
  width: 2rem;
  float: left;
  font-size: 1.5rem;
  margin-right: 1rem;
`;
let Username = styled.div`
  font-weight: 1rem;
  font-size: 1.5rem;
`;
let BottomBox = styled.div`
  border-radius: 1rem;
  background: white;
  padding: 0.5rem;
`;
let LikeText = styled.div`
  margin-left: 5rem;
`;
let Icons = styled.div`
  float: left;
`;
let Contents = styled.div`
  margin: 0.5rem;
`;

function Feeds() {
  const likes = 10;
  return (
    <FeedWrap>
      <Idwrap>
        <ProfileImg>ㅁ</ProfileImg>
        <Username>아이디</Username>
      </Idwrap>
      <div
        style={{
          background: "lightgrey",
          width: "37rem",
          height: "30rem",
          margin: "auto",
          borderRadius: "1rem",
        }}></div>
      <BottomBox>
        <LCwrap>
          <Icons>
            <ThumbUpOffAltIcon />
            <AddCommentIcon />
          </Icons>
          <LikeText>{likes}명이 좋아합니다.</LikeText>
        </LCwrap>
        <Title>제목 : 블랙핑크 노래 좋습니다.</Title>
        <HashTag>해시태그 : #블랙핑크</HashTag>
        <Contents>블랙핑크 노래가 아주 좋습니다.</Contents>
      </BottomBox>
    </FeedWrap>
  );
}
export default Feeds;
