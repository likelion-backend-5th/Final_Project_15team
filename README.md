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





# 마이페이지




