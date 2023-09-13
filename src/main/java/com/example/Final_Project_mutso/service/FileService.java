package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedFile;
import com.example.Final_Project_mutso.repository.FeedFileRepository;
import com.example.Final_Project_mutso.repository.FeedRepository;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final FeedFileRepository feedFileRepository;
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

        return fileUrl;
    }

    public String updateFile(Long feedId, MultipartFile file) throws IOException {
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        if (optionalFeed.isPresent()){
            // 저장되었던 이미지 불러오기

            if (!file.isEmpty()) { // 첨부 파일이 존재한다면

                String url = createFile(file);
                FeedFile feedFile = new FeedFile();
                feedFile.setFeed(optionalFeed.get());
                feedFile.setImageUrl(url);
                feedFileRepository.save(feedFile);

            }
        }
        return null;
    }

    public String readFile(Long feedId) {//!!!!!!!게시글 id 제발!!!!! file id아니고!!!!
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        if (optionalFeed.isPresent()) {
            // 저장되었던 이미지 불러오기
            for (FeedFile feedFile :
                    feedFileRepository.findAll()) {

                if (feedFile.getImageUrl() != null) {
                    if (feedFile.getFeed().getId().equals(feedId)) {
                        String imageUrl = feedFile.getImageUrl();
                        return imageUrl;
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
            for (FeedFile feedFile :
                    feedFileRepository.findAll()) {

                if (feedFile.getImageUrl() != null) {
                    if (feedFile.getFeed().getId().equals(feedId)) {
                        feedFileRepository.deleteById(feedFile.getId());
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
