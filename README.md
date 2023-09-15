# 🎼 Music SNS Platform 📱
음악 애호가들을 대상으로 한 음악 SNS 서비스

## 📂   프로젝트 소개
- 해당 프로젝트는 기본적으로 CRUD를 구현
- 로그인, 회원가입, 마이페이지, 팔로우 & 팔로잉 관리, 스크랩 기능 구현
- 자신만의 개성을 나타낼 수 있는 피드 생성 / 수정 / 삭제 기능
- 각 피드에 대한 댓글 기능 및 좋아요 기능 구현
- 관련 게시물을 나타내는 해시태그 기능 및 해시태그 검색 기능
- WebSocket + Stomp를 활용한 실시간 채팅 기능 구현
- Youtube api를 활용한 음원 스트리밍 (음악 플레이어) 기능 구현
- WebSecurity를 적용하여 Jwt 토큰을 이용한 사용자 인증, 권한 부여
- 리액트를 활용한 웹 페이지 구현

## 🗒️  테스트 방법

1. Final_Project_15Team의 Repository Clone

2. POSTMAN_COLLECTION.json 파일을 다운로드 한다.

3. MySql 연결 후 테스트


## 📓 각 API 및 반환값
<details>
  
<summary> Postman </summary>  

# 채팅
- 채팅방 생성 : `POST/chat/rooms` / response: `{"id": 3,"roomName": "room1","userCount": 0,"imageUrl": "이미지 url"}`
<img width="853" alt="image" src="https://github.com/likelion-backend-5th/MiniProject_Basic_kimhyeonjeong/assets/128394219/602119ec-5cc5-4fec-b268-96ac1a5a0002">

- 채팅방 리스트 조회 : `GET/chat/rooms` / response: `{"id":"1","roomName":"name"}`
<img width="855" alt="image" src="https://github.com/likelion-backend-5th/MiniProject_Basic_kimhyeonjeong/assets/128394219/351d6684-cea7-4f3e-9590-734a7eef87ea">

- 유저 정보 불러오기 : `GET /chat/userInfo` / response : `{ "username": "","nickname": "","profileImage": ""}`
<img width="853" alt="image" src="https://github.com/likelion-backend-5th/MiniProject_Basic_kimhyeonjeong/assets/128394219/3b7b5741-15f8-47ec-931b-040566d28348">

- 채팅방 미디어 파일 전송하기 : `POST /chat/image` / response : 
  - (+ Multipartfile로 받은 파일 저장 후 이미지 url 생성 및 반환 하는 역할)
<img width="852" alt="image" src="https://github.com/likelion-backend-5th/MiniProject_Basic_kimhyeonjeong/assets/128394219/94bc2028-6f29-4952-9ac1-5de6dec789d5">

- 인원수 조회 (채팅방 정보 Dto) : `GET/chat/rooms` / response: `{"id":"1","roomName":"name","userCount":2,"imageUrl":"url"}`
<img width="858" alt="image" src="https://github.com/likelion-backend-5th/MiniProject_Basic_kimhyeonjeong/assets/128394219/f3449adc-dee2-41d9-a42a-35ab4fb337e2">


- 메세지 보내기 : `/app/chat/messsage`(@MessageMapping("/chat/message"))
- 소켓 연결 끊기 (채팅방 나가기) : `app/chat/eixt` (@MessageMapping("/chat/exit"))
- 채팅방 입장 : `/chat/room/enter/{roomId}`
- 채팅방 입장 메세지 : `/app/chat/enter`(@MessageMapping("/chat/enter"))


# 피드
- 피드 생성 : `POST/feed/add`
- ![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/72905123/41cdcb80-d919-4166-b308-d78b99c121e8)
- dto
- {"title": "title", "content": "test"}
- hashtag
- #hash #test

- 피드 목록 : `GET/feed`
- 피드 상세 : `GET/feed/{feedId}`
- 피드 수정 : `PUT/feed/{feedId}`
- dto
- {"title": "titlEdit", "content": "editTest"}
- hashtag
- #hash #test2

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

- 해시태그 검색 : `GET/feed/hashSearch`
- ![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/72905123/7de720ee-2a05-4d0c-8885-181cbd20d5b4)

- 좋아요/좋아요 취소 : `POST/{feedId}/like`
- 좋아요 개수 : `GET/{feedId}/like`


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
- Post http://localhost:8080/users/register

params에 key랑 value 입력 (회원가입정보)
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/5c5423ea-0968-497c-bc1d-7fde707e2ba5)

- 로그인
- Post http://localhost:8080/users/login

body raw json username, password 입력하면 토큰 나옴
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/02ed8961-9f5b-4f7e-81a2-83f60789e5db)

- 토큰 검증 후 유저 데이터 전달
- Post http://localhost:8080/users/secure-resource

Authorization Bearer Token 토큰 입력
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/b96f9952-98d1-4abb-8557-29777b31b695)

- 팔로우
- Put http://localhost:8080/users/mypage/{username}/follow

auth bearer token 에서 토큰 입력 후 username에 팔로우 할 아이디 적으면 됨
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/fe52d3ba-a5f3-47a5-81cf-81a0b86450b6)

- 팔로우리스트
- Get http://localhost:8080/users/mypage/{username}/follow

auth bearer token 에서 토큰 입력 후 username에는 팔로우 리스트 보고싶은 아이디 적으면 됨
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/087790b6-5e26-4c10-9c35-00c25fbfb98b)

- 마이페이지
- Get http://localhost:8080/users/mypage/{username}

auth bearer token 에서 토큰 입력
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/f7d67d8e-ee9c-45b8-a7dc-31bc6b38b900)

- 프로필
- Get http://localhost:8080/users/mypage/{username}/profile

auth bearer token 에서 토큰 입력
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/813519eb-9fac-47ae-9c76-fa6a462abbc8)

- 프로필 이미지
- Put http://localhost:8080/users/mypage/profile/imgupload

Params key: nickname, introduction value: 입력, auth bearer token 에서 토큰 입력, body form-data key: image (file) value: 파일찾기
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/47e32cf8-4394-41cf-b504-813ddf1cd943)

![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/281fad83-bf83-4ffd-9289-43d7319b87b5)

  
- 스크랩
- Put http://localhost:8080/users/1/scrap
users 다음에 오는 값은 피드 아이디, auth bearer token 에서 토큰 입력
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/1eda96b8-f79b-4dac-a421-f1829c5b4a5a)

- 스크랩 리스트
- Get http://localhost:8080/users/mypage/1/scrap
mypage 다음에 오는 값은 피드 아이디, 그래서 피드 아이디에 대해 있으면 피드 타이틀이 뜨고 없으면 null
![image](https://github.com/likelion-backend-5th/Final_Project_15team/assets/103910358/fcc61223-0fa0-4351-91b0-f311c5f2e466)
=======
</details>


##  🏁 프로젝트 개발 과정


### ⏲️ 개발 기간
- 2023.08.09 ~ 2023.09.15 ( 총 38일 )


### ⚙️  개발 환경 
- JDK 1.7
- Java 8
- Gradle


### 🔨 개발 도구

#### [BackEnd]
- Language : java
- Framework : Spring boot
- IDE : IntelliJ IDEA
- DataBase : MySQL
- API Platform : Postman


#### [FrontEnd]
- Language : Html, JS, CSS
- Library : React

  
#### [협업 Tools]
- Git
- Notion
- Discord

  
## 🗂️ 프로젝트 설계도

### ERD
![image](https://github.com/likelion-backend-5th/MiniProject_Basic_kimhyeonjeong/assets/128394219/2b17b732-bd3a-4f04-902c-93dd5c21815a)



### IA (Information Architecture)
![image](https://github.com/likelion-backend-5th/MiniProject_Basic_kimhyeonjeong/assets/128394219/ed00a068-6de3-4c85-aa7e-ac412ee179da)



## 🎤 시스템 구현

- [ 로그인 및 회원가입 ]   
  웹에서 입력 받은 값을 서버에서 저장, 로그인 시 JWT 토큰이 발급되며, 웹과 서버 간 검증을 거쳐 결과적으로 유저 정보를 웹에게 전달하도록 구현

- [ 팔로우 및 팔로잉 ]   
  팔로우 할 유저 네임과 로그인 한 유저 네임을 비교하여 팔로우 버튼의 활성화/ 비활성화를 구분. 팔로우 시 마이페이지에서 팔로워/ 팔로잉 상세보기를 보게되면 프로필이미지, 닉네임, 소개가 보이도록 구현

- [ 피드 관리 ]   
  피드 생성/수정/삭제 기능 구현 및 해시태그를 이용한 검색 기능

- [ 음원 플레이어 ]    
  Youtube api의 search() 를 통해서 검색하고자 하는 music의 아이디 , 업로드 채널, 재생 시간 등의 정보를 받아 프론트에 전달하고,   
  youtube iframe api의 플레이어 기능을 통해 받아온 id값을 통해 music을 재생하는 player를 구현

- [ 실시간 채팅 ]   
  Stomp와 WebSocket을 이용하여 실시간 오픈채팅방 구현. 메시지 브로커를 통해 특정 구독한 쪽에만 메시지를 전송하도록 설정하여 입장한 채팅방의 roomId를 구독하도록 설정 후 해당 채팅방에서만 채팅이 가능하도록 구현

- [ 권한설정 ]   
  GET으로 들어온 요청들은 모두 허용(permitAll)으로설정. 그 외 편집기능들은 .authenticated() 설정하여 인증 절차를 거치도록 설정,
  인증 절차가 필요한 각 기능에 user 정보 추가 및 연결,   
  채팅같은 경우 메세지를 발행하기 전, 메세지에 대한 전처리를 수행하는 PreHandler 설정 후 InBoundChannel을 가로채서 JWT 검증 수행

