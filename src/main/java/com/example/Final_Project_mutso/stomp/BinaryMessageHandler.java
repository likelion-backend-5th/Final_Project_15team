//package com.example.Final_Project_mutso.stomp;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.BinaryMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.File;
//import java.nio.ByteBuffer;
//
//@Component
//public class BinaryMessageHandler extends TextWebSocketHandler {
//    @Override
//    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
//        //바이너리 메시지 발송
//        ByteBuffer byteBuffer = message.getPayload();
//        String filename = "image.jpg";
//        File dir = new File(FILE_UPLOAD_PATH, filename);
//}
