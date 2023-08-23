package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.YoutubeVideoDto;
import com.example.Final_Project_mutso.entity.MusicEntity;
import com.example.Final_Project_mutso.entity.MusicPlayList;
import com.example.Final_Project_mutso.entity.User;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.MusicRepository;
import com.example.Final_Project_mutso.repository.PlayListRepository;
import com.example.Final_Project_mutso.repository.UserRepository;
import com.google.api.client.http.OpenCensusUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import com.google.api.services.youtube.model.ChannelContentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private static List<MusicEntity> playList;
    private final MusicRepository musicRepository;
    private final PlayListRepository playListRepository;
    private final UserRepository userRepository;

    static {
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-cmdline-search-sample").build();
    }
    public List<YoutubeVideoDto> search(String searchQuery, int maxSearch){
        log.info("Starting Youtube Search... " + searchQuery);
        List<YoutubeVideoDto> rvalue = new ArrayList<YoutubeVideoDto>();
        playList = new ArrayList<>();

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
                        MusicEntity entity = new MusicEntity();
                        entity.setMusicName(searches.getSnippet().getTitle());
                        entity.setImageUrl(searches.getSnippet().getThumbnails().getDefault().getUrl());
                        entity.setMusicId(GOOGLE_YOUTUBE_URL + searches.getId().getVideoId());
                        entity.setArtist(searches.getSnippet().getChannelTitle());

                        VideoListResponse videoListResponse = youtube.videos().list("contentDetails")
                                        .setKey(apiKey).setId(searches.getId().getVideoId()).execute();
                        List<Video> videoList = videoListResponse.getItems();
                        if (!videoList.isEmpty()) {
                            String duration = videoList.get(0).getContentDetails().getDuration();
                            Duration videoDuration = Duration.parse(duration);

                            long minutes = videoDuration.toMinutes();
                            long seconds = videoDuration.minusMinutes(minutes).getSeconds();

                            String formattedDuration = String.format("%d:%02d", minutes, seconds);

                            entity.setMusicTime(formattedDuration);

                        }
                        musicRepository.save(entity);
                        playList.add(entity);

                        rvalue.add(YoutubeVideoDto.fromEntity(entity));
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

    public MusicPlayList addPlayList(String playListName, int musicId) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername("test");
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<MusicPlayList> optionalMusicPlayList = playListRepository.findByName(playListName);
        if (optionalMusicPlayList.isEmpty()) {
            MusicPlayList musicPlayList = new MusicPlayList();
            musicPlayList.setName(playListName);
            musicPlayList.setUser(optionalUser.get());

            musicPlayList.getPlayList().add(playList.get(musicId - 1));
            playListRepository.save(musicPlayList);
            return musicPlayList;
        } else {
            MusicPlayList musicPlayList = optionalMusicPlayList.get();
            musicPlayList.getPlayList().add(playList.get(musicId - 1));
            playListRepository.save(musicPlayList);
            return musicPlayList;
        }
    }

    public MusicEntity returnVideo(int musicId){

        return playList.get(musicId-1);
    }

    public List<String> getPlayList() {
        List<String> myPlaylist = new ArrayList<>();
        Optional<UserEntity> testUser = userRepository.findByUsername("test");
        if(testUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity user = testUser.get();


        for(MusicPlayList list : playListRepository.findByUser(user))
            myPlaylist.add(list.getName());

        return myPlaylist;

    }
}
