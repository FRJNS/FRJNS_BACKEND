package com.gdsc.frjns.videos.controller;

import com.gdsc.frjns.videos.domain.model.Video;
import com.gdsc.frjns.videos.dto.VideoResponse;
import com.gdsc.frjns.videos.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/search")
    public ResponseEntity<String> searchVideos() throws GeneralSecurityException, IOException {
        videoService.saveVideos();

        return ResponseEntity.ok("New videos saved successfully.");
    }

    @GetMapping("/videos")
    public ResponseEntity<Slice<VideoResponse>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Slice<VideoResponse> videoResponses = videoService.findAll(pageable);

        if (videoResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(videoResponses);
    }
}
