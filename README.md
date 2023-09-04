# 채팅
- 채팅방 생성 : endPoint : `POST/chat/rooms` / response: `{"id":"1","roomName":"name"}`
- 채팅방 리스트 조회 : endPoint : `GET/chat/rooms` / response: `{"id":"1","roomName":"name"}`
- 채팅방 메세지 조회 : endPoint : `GET/chat/rooms/{id}/message`   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
response: `{"roomId": 1,"sender": "d","message": "fff","time": "15:36"}`
- 채팅방 삭제 : endPoint : `DELETE/chat/rooms/{id}` / response: void로 설정했기 때문에 반환값 없음
- 메세지 보내기 : `Message/chat/messsage`(@MessageMapping("/chat/message"))
- 채팅방 입장 : `Message/chat/enter`(@MessageMapping("/chat/enter"))
- 채팅방 퇴장 : Websocket 세션이 종료될 시 Spring boot 내에서 유저 퇴장 메서드 발생





# 피드
- 피드 생성 : `POST/feed/add`
- ![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/72905123/49856f73-66e9-467b-8952-5c4d1ee2d8bb)
- 
- 피드 목록 : `GET/feed`
- 피드 상세 : `GET/feed/{feedId}`
- 피드 수정 : `PUT/feed/{feedId}`
- ![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/72905123/d9bf7a40-6dd7-44a4-b959-4f81b7fa420e)

- 피드 삭제 : `DELETE/feed/{feedId}`
  
- 댓글 생성 : `POST/comment/{feedId}`
  {
    "content": "test"
  }
- 댓글 불러오기(오류, 피드엔티티에 댓글 항목 있어서 피드 불러오기도 오류 발생) : `GET/comment/{feedId}`
- 댓글 수정 : `PUT/comment/{feedId}/{commentId}`
  {
    "content": "edit"
  }
- 댓글 삭제 : `DELETE/comment/{feedId}/{commentId}`



# 뮤직플레이어
- 음악 검색
  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/560c90ed-5daa-419f-b989-02c81433a7dd)
  
  "title":"[최초 공개] DK (디셈버) - 心 (심)"
  "imageUrlPath":"https://i.ytimg.com/vi/OMjDI2NqQ9M/default.jpg"
  "videoId":"https://www.youtube.com/watch?v=OMjDI2NqQ9M"
  "artist":"방구석 캐스팅","musicTime":"4:17"
  
  의 형태로 리턴되게 됩니다.
  
  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/3e43a051-0840-4c70-a73a-6803704a763c)
  
  dto의 타입은 다음과 같습니다.

- 검색한 음악 재생
  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/9f0e5d61-b11f-4280-8dbc-34b9b05ef780)
  
  /search/{musicId}/playmusic
  musicId는 검색한 리스트의 순서를 의미합니다.
  
  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/4e46ddee-f42d-497a-a584-86c061a51300)
  
  음악 url을 보내줄수 있으며, 위에서 검색할때 나온 title, artist , musicTime, imageUrl을 모두 보낼 수 있으며, 필요한 정보가 있다면 추가 개발 하도록 하겠습니다.


# 플레이리스트

- 검색한 음악 플레이 리스트 추가
  
  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/73d4903f-a467-468a-ab83-3942e8adb4b3)
  
  search/{musicId}/{playListName}/add
  
  음악을 {playListName}이라는 플레이 리스트에 저장합니다. 플레이 리스트가 이미 존재한다면 추가를 하고, 없다면 새롭게 만듭니다.

  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/0483a0cb-89b1-42ef-a7f2-8b9d787f03ea)

- 내 플레이  리스트 조회
  
  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/27664767-cc8f-4c08-a40c-74ffc809f10d)

  내 모든 플레이리스트들을 조회합니다.
  
- 나의 특정 플레이 리스트 조회

  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/bba74f95-d3d9-4400-945a-c6ec8a47fda6)

  youtube/myplaylist/{playlistname}
  
  나의 특정 플레이리스트를 조회합니다.

  playlistname에 조회하고자 하는 플레이리스트의 이름을 넣으면 됩니다.


  

  


# 마이페이지
- 회원가입
Post http://localhost:8080/users/register
params에 key랑 value 입력 (회원가입정보)

- 로그인
Post http://localhost:8080/users/login
body raw json username, password 입력하면 토큰 나옴

- 팔로우
Put http://localhost:8080/users/mypage/{username}/follow
auth bearer token 에서 토큰 입력 후 username에 팔로우 할 아이디 적으면 됨

- 팔로우리스트
Get http://localhost:8080/users/mypage/{username}/follow
auth bearer token 에서 토큰 입력 후 username에는 팔로우 리스트 보고싶은 아이디 적으면 됨

- 마이페이지
Get http://localhost:8080/users/mypage/{username}
auth bearer token 에서 토큰 입력

- 프로필
Get http://localhost:8080/users/mypage/{username}/profile
auth bearer token 에서 토큰 입력

- 프로필 이미지
Put http://localhost:8080/users/mypage/profile/imgupload
auth bearer token 에서 토큰 입력 후 body form-data key: image (file) value: 파일찾기
