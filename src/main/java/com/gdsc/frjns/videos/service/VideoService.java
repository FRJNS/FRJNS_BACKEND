package com.gdsc.frjns.videos.service;

import com.gdsc.frjns.videos.domain.model.Video;
import com.gdsc.frjns.videos.domain.repository.VideoRepository;
import com.gdsc.frjns.videos.dto.VideoResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VideoService {
    private static final String API_KEY = "AIzaSyAs1bLuaAve9UlP4wYkq3ubPuqulLW764o";

    private final VideoRepository videoRepository;

    public void saveVideos() throws IOException, GeneralSecurityException {
        YouTube youtube = getYouTubeService();

        YouTube.Search.List search = youtube.search().list("snippet");
        search.setKey(API_KEY);
        search.setQ("뉴진스 세로직캠");
        search.setType("video");
        search.setMaxResults(50L); // 원하는 최대 결과 수

        SearchListResponse response = search.execute();
        List<SearchResult> searchResults = response.getItems();

        List<Video> videos = new ArrayList<>();
        for (SearchResult searchResult : searchResults) {
            String title = searchResult.getSnippet().getTitle().replace("&#39;", "'");
            String channelId = searchResult.getId().getVideoId();

            // 이미 저장된 Video인지 확인
            Optional<Video> existingVideo = videoRepository.findByChannelId(channelId);
            if (existingVideo.isPresent()) {
                // 이미 저장된 Video는 건너뛰기
                continue;
            }

            Video video = Video.builder()
                    .title(title)
                    .channelId(channelId)
                    .build();
            videos.add(video);
        }

        // 새로운 Video만 저장
        videoRepository.saveAll(videos);
    }

    public Slice<VideoResponse> findAll(Pageable pageable) {
        Slice<Video> videoSlice = videoRepository.findAllBy(pageable);

        return videoSlice.map(Video::toResponse);
    }

    private YouTube getYouTubeService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, new JacksonFactory(), request -> {
        }).build();
    }
}
