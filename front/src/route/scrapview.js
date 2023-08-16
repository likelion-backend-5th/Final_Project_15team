import "bootstrap/dist/css/bootstrap.min.css";
import styled from "styled-components";

import HomeIcon from "@mui/icons-material/Home";
import SettingsIcon from "@mui/icons-material/Settings";
import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import ContentCutIcon from "@mui/icons-material/ContentCut";

let WholeWrap = styled.div``;
let TopWrap = styled.div`
  display: flex;
`;
let ContentWrap = styled.div``;
let BottomWrap = styled.div``;

let ProfileImg = styled.div`
  background: grey;
  border-radius: 2rem;
  width: 4rem;
  height: 4rem;
`;
let Title = styled.div`
  font-size: 2rem;
`;
let Content = styled.div``;
let Hashtag = styled.div``;

function ScrapView() {
  return (
    <>
      <WholeWrap>
        <TopWrap>
          <ProfileImg>a</ProfileImg>
          이름
        </TopWrap>
        <ContentWrap>
          <Title>제목:굵은글씨</Title>
          <Content>내용</Content>
          <Hashtag>#해시태그</Hashtag>
        </ContentWrap>
        <BottomWrap>
          <ThumbUpOffAltIcon></ThumbUpOffAltIcon>
          <ContentCutIcon></ContentCutIcon>
        </BottomWrap>
      </WholeWrap>
    </>
  );
}

export default ScrapView;
