package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedImage;
import com.example.Final_Project_mutso.entity.FeedVideo;
import com.example.Final_Project_mutso.repository.FeedImageRepository;
import com.example.Final_Project_mutso.repository.FeedRepository;
import com.example.Final_Project_mutso.repository.FeedVideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final FeedImageRepository feedImageRepository;
    private final FeedVideoRepository feedVideoRepository;
    private final FeedRepository feedRepository;

    public String createFile(MultipartFile file) throws IOException {

        String fileDir = "back/feed/";
        try {
            Files.createDirectories(Path.of(fileDir));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        file.transferTo(Path.of(fileDir+fileName));

        // 이미지 URL 생성
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("static/feed/") // 이미지 업로드 경로
                .path(fileName)
                .toUriString();

//        String fileName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
//        long size = file.getSize(); //파일 사이즈
//
//        System.out.println("파일명 : "  + fileName);
//        System.out.println("용량크기(byte) : " + size);
//        //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
//        String fileExtension = fileName.substring(fileName.lastIndexOf("."),fileName.length());
//        String uploadFolder = System.getProperty("user.dir") + "\\files";
//
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid.toString());
//        String[] uuids = uuid.toString().split("-");
//
//        String uniqueName = uuids[0];
//        System.out.println("생성된 고유문자열" + uniqueName);
//        System.out.println("확장자명" + fileExtension);
//
////             java.io.File saveFile = new java.io.File(uploadFolder+"\\"+fileName);
//
//        java.io.File saveFile = new java.io.File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
//        String fileUrl = String.format(System.getProperty("user.dir") + "\\files\\%s", uniqueName+fileExtension);
////            String fileUrl = uploadFolder+"\\"+fileName;
//        try {
//            file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return fileUrl;
    }

    public String updateFile(Long feedId, MultipartFile file) throws IOException {
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        if (optionalFeed.isPresent()){
            // 저장되었던 이미지 불러오기
            for (FeedImage feedImage :
                    feedImageRepository.findAll()) {

                if (feedImage.getImageUrl() != null){
                    if (feedImage.getFeed().equals(feedId)){
                        String imageUrl = feedImage.getImageUrl();
                        return imageUrl;
                    }
                }
            }
            // 저장되었던 영상 불러오기
            for (FeedVideo feedVideo :
                    feedVideoRepository.findAll()) {

                if (feedVideo.getVideoUrl() != null){
                    if (feedVideo.getFeed().equals(feedId)){
                        String videoUrl = feedVideo.getVideoUrl();
                        return videoUrl;
                    }
                }
            }
            if (!file.isEmpty()) { // 첨부 파일이 존재한다면
                String fileName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
                String fileExtension = fileName.substring(fileName.lastIndexOf("."),fileName.length());

                if (fileExtension.equals(".jpg")||fileExtension.equals(".png")){// 확장자가 이미지일 때
                    String url = createFile(file);
                    FeedImage feedImage = new FeedImage();
                    feedImage.setFeed(optionalFeed.get());
                    feedImage.setImageUrl(url);
                    feedImageRepository.save(feedImage);
                } else if (fileExtension.equals(".mp4")||fileExtension.equals(".avi")){ //영상 확장자일 때
                    String url = createFile(file);
                    FeedVideo feedVideo = new FeedVideo();
                    feedVideo.setFeed(optionalFeed.get());
                    feedVideo.setVideoUrl(url);
                    feedVideoRepository.save(feedVideo);
                }
                else{
                    System.out.println(".jpg, .png, .mp4, .avi 확장자 파일을 선택해주세요");
                }
            }
        }
        return null;
    }

    public String readFile(Long feedId) {//!!!!!!!게시글 id 제발!!!!! file id아니고!!!!
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        if (optionalFeed.isPresent()) {
            // 저장되었던 이미지 불러오기
            for (FeedImage feedImage :
                    feedImageRepository.findAll()) {

                if (feedImage.getImageUrl() != null) {
                    if (feedImage.getFeed().getId().equals(feedId)) {
                        String imageUrl = feedImage.getImageUrl();
                        return imageUrl;
                    }
                }
            }
            // 저장되었던 영상 불러오기
            for (FeedVideo feedVideo :
                    feedVideoRepository.findAll()) {

                if (feedVideo.getVideoUrl() != null) {
                    if (feedVideo.getFeed().getId().equals(feedId)) {
                        String videoUrl = feedVideo.getVideoUrl();
                        return videoUrl;
                    }
                }
            }
        }

        return null;
    }

    public void deleteFile(Long feedId) {
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        if (optionalFeed.isPresent()) {
            // 저장되었던 이미지 찾아 삭제
            for (FeedImage feedImage :
                    feedImageRepository.findAll()) {

                if (feedImage.getImageUrl() != null) {
                    if (feedImage.getFeed().getId().equals(feedId)) {
                        feedImageRepository.deleteById(feedImage.getFeed().getId());
                    }
                }
            }
            // 저장되었던 영상 찾아 삭제
            for (FeedVideo feedVideo :
                    feedVideoRepository.findAll()) {

                if (feedVideo.getVideoUrl() != null) {
                    if (feedVideo.getFeed().getId().equals(feedId)) {
                        feedVideoRepository.deleteById(feedVideo.getFeed().getId());
                    }
                }
            }
        }
    }



//    public void downFile(File file, HttpServletResponse response) throws IOException {
//        String uploadFolder = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//
//        java.io.File saveFile = new java.io.File(uploadFolder+"\\"+file.getName());
//
//        // file 다운로드 설정
//        response.setContentType("application/download");
//        response.setContentLength((int)saveFile.length());
//        response.setHeader("Content-disposition", "attachment;filename=\"" + file.getName() + "\"");
//        // response 객체를 통해서 서버로부터 파일 다운로드
//        OutputStream os = response.getOutputStream();
//        // 파일 입력 객체 생성
//        FileInputStream fis = new FileInputStream(saveFile);
//        FileCopyUtils.copy(fis, os);
//        fis.close();
//        os.close();
//    }



}
