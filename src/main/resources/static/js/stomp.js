'use strict';

// document.write("<script src='jquery-3.6.1.js'></script>")
document.write("<script\n" +
    "  src=\"https://code.jquery.com/jquery-3.6.1.min.js\"\n" +
    "  integrity=\"sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=\"\n" +
    "  crossorigin=\"anonymous\"></script>")

let usernamePage = document.querySelector('#username-page');
let chatPage = document.querySelector('#chat-page');
let usernameForm = document.querySelector('#usernameForm');
let messageForm = document.querySelector('#messageForm');
let messageInput = document.querySelector('#message');
let messageArea = document.querySelector('#messageArea');
let connectingElement = document.querySelector('.connecting');

let stompClient = null;
let username = null;

let colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

// roomId 파라미터 가져오기
// 현재 페이지의 전체 URL
const url = new URL(location.href).searchParams;
const roomId = url.get('roomId');

function connect(event){

    username = document.querySelector('#name').value.trim();

    // usernamePage 에 hidden 속성 추가해서 가리고 chatPage를 등장시킴
    usernamePage.classList.add('hidden');
    chatPage.classList.remove('hidden');

    // 연결하고자하는 Socket 의 endPoint
    let socket = new SockJS('/ws/chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({},onConnected,onError);

    event.preventDefault();
}

function onConnected() {

    // sub 할 url => /sub/chat/room/roomId 로 구독한다
    stompClient.subscribe('/sub/chat/room/' + roomId, onMessageReceived);

    // /pub/chat/enterUser 로 Json 형태의 메시지를 보냄
    stompClient.send("/pub/chat/enterUser",
        {},
        JSON.stringify({
            "roomId": roomId,
            sender: username,
            type: 'ENTER'
        })
    )

    // HTML 문서에서 특정 요소를 가리키는 변수
    connectingElement.classList.add('hidden');

}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

// 메시지 전송때는 JSON 형식을 메시지를 전달한다.
function sendMessage(event) {
    let messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        let chatMessage = {
            "roomId": roomId,
            sender: getUserNickname(),
            message: messageInput.value,
            type: 'TALK'
        };

        stompClient.send("/pub/chat/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

// 현재 사용자의 닉네임을 가져오는 함수
function getUserNickname() {
    // 이 부분에 서버로부터 현재 사용자의 닉네임을 가져오는 코드를 추가합니다.
    // 예를 들어, API 요청을 통해 닉네임을 가져올 수 있습니다.
    // 아래는 예시 코드일 뿐 실제 구현 방법은 백엔드에 따라 다를 수 있습니다.
    let nickname = ""; // 서버로부터 닉네임을 가져오는 api 추가
    return nickname;
}


// 메시지를 받을 때도 마찬가지로 JSON 타입으로 받으며,
// 넘어온 JSON 형식의 메시지를 parse 해서 사용한다.
// 서버로부터 메시지를 수신받아 채팅 화면에 표시하는 역할
function onMessageReceived(payload) {
    // payload 매개변수는 WebSocket으로 받은 메시지 객체를 나타냄
    let chat = JSON.parse(payload.body);

    // 채팅 메시지를 표시할 <li> 요소를 JavaScript로 동적으로 생성하는 부분
    // 여기서 messageElement는 채팅 메시지 하나를 표시할 <li> 요소를 나타냄
    // 이 요소는 나중에 메시지 내용과 스타일을 추가하여 실제로 화면에 표시되는 역할
    let messageElement = document.createElement('li');

    // 입장
    if (chat.type === 'ENTER') {  // chatType 이 enter 라면 아래 내용
        messageElement.classList.add('event-message');
        chat.content = chat.sender + chat.message;
        // 사용자가 채팅방에 입장했을 때, 사용자 목록을 업데이트하기 위해 호출하는 함수
        // getUserList();

        // 나가기
    } else if (chat.type === 'EXIT') { // chatType 가 exit 라면 아래 내용
        messageElement.classList.add('event-message');
        chat.content = chat.sender + chat.message;
        // getUserList();

        // 대화
    } else {
        // chatType 이 talk 라면 아래 내용
        messageElement.classList.add('chat-message');

        // 대화 상대의 아바타(프로필 아이콘)를 나타내기 위해 <i> 요소 생성
        let avatarElement = document.createElement('i');
        // 아바타 아이콘 내에 텍스트 노드를 생성. 이 텍스트 노드는 발송자(sender)의 첫 글자를 나타냄
        let avatarText = document.createTextNode(chat.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(chat.sender);

        messageElement.appendChild(avatarElement);

        let usernameElement = document.createElement('span');
        let usernameText = document.createTextNode(chat.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    let contentElement = document.createElement('p');

    // 채팅 메시지 요소를 채팅 영역에 추가
    messageArea.appendChild(messageElement);
    // 채팅 영역의 스크롤 위치를 맨 아래로 이동시켜서 최신 메시지를 표시
    messageArea.scrollTop = messageArea.scrollHeight;
}

// 사용자의 이름을 기반으로 아바타의 색상을 결정하는 함수
function getAvatarColor(messageSender) {
    let hash = 0;
    for (let i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    let index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)