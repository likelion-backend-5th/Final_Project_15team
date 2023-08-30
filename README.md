# 채팅
- 채팅방 생성 : endPoint : `POST/chat/rooms` / response: `{"id":"1","roomName":"name"}`
- 채팅방 리스트 조회 : endPoint : `GET/chat/rooms` / response: `{"id":"1","roomName":"name"}`
- 채팅방 메세지 조회 : endPoint : `GET/chat/rooms/{id}/message`   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
response: `{"roomId": 1,"sender": "d","message": "fff","time": "15:36"}`
- 채팅방 삭제 : endPoint : `DELETE/chat/rooms/{id}` / response: void로 설정했기 때문에 반환값 없음





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

- 검색한 음악 플레이 리스트 추가
  
  ![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/73d4903f-a467-468a-ab83-3942e8adb4b3)
  
  search/{musicId}/{playListName}/add
  
  음악을 {playListName}이라는 플레이 리스트에 저장합니다. 플레이 리스트가 이미 존재한다면 추가를 하고, 없다면 새롭게 만듭니다.

![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/0483a0cb-89b1-42ef-a7f2-8b9d787f03ea)

- 내 플레이  리스트 조회

![제목 없음](https://github.com/likelion-backend-5th/Final_Project_15team/assets/109780232/b520c245-50af-4793-81c6-bfc36f646b78)

- 



# 마이페이지




