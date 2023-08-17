package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.YoutubeVideoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YoutubeVideoService {
    private static Logger log = LoggerFactory.getLogger(YoutubeVideoService.class);
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final long NUMBER_OF_VIDEOS_RETURNED = 5;  // 검색 개수
    private static final String GOOGLE_YOUTUBE_URL =  "https://www.youtube.com/watch?v=";
    private static final String YOUTUBE_SEARCH_FIELDS = "items(id/kind,id/videoId,snippet/title," +
            "snippet/description,snippet/channelTitle,snippet/thumbnails/default/url)";
    private static final String YOUTUBE_APIKEY = "AIzaSyAj95x7jyV6YJCg1owyMqMoRTN-PHe15-E";

    private static YouTube youtube;

    static {
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-cmdline-search-sample").build();
    }
    public static List<YoutubeVideoDto> search(String searchQuery, int maxSearch){
        log.info("Starting Youtube Search... " + searchQuery);
        List<YoutubeVideoDto> rvalue = new ArrayList<YoutubeVideoDto>();

        try{
            if(youtube != null){
                YouTube.Search.List search = youtube.search().list("id,snippet");
                String apiKey = YOUTUBE_APIKEY;
                search.setKey(apiKey);
                search.setQ(searchQuery);
                search.setType("video");

                String youtubeFields = YOUTUBE_SEARCH_FIELDS;
                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

                if(youtubeFields != null && !youtubeFields.isEmpty()){
                    search.setFields(youtubeFields);
                }

                SearchListResponse searchResponse = search.execute();
                List<SearchResult> searchResultList = searchResponse.getItems();

                if(searchResultList != null){
                    for(SearchResult searches : searchResultList){
                        YoutubeVideoDto dto = new YoutubeVideoDto();
                        dto.setTitle(searches.getSnippet().getTitle());
                        dto.setImageUrlPath(searches.getSnippet().getThumbnails().getDefault().getUrl());
                        dto.setVideoId(GOOGLE_YOUTUBE_URL + searches.getId().getVideoId());
                        rvalue.add(dto);
                        log.info("title : " + searches.getSnippet().getTitle());
                    }
                }

            }
        } catch (GoogleJsonResponseException e){
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch(IOException e){
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch(Throwable t){
            t.printStackTrace();
        }
        return rvalue;

    }
}
