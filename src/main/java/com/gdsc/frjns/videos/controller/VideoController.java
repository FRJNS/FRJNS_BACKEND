package com.gdsc.frjns.videos.controller;

import com.gdsc.frjns.news.dto.NewsDTO;
import com.gdsc.frjns.videos.domain.model.Video;
import com.gdsc.frjns.videos.dto.VideoResponse;
import com.gdsc.frjns.videos.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "video 데이터 추가",
            description = "'뉴진스 세로직캠' 키워드 검색에 대한 새로운 video 데이터를 추가합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공")
            }
    )
    public ResponseEntity<String> searchVideos() throws GeneralSecurityException, IOException {
        videoService.saveVideos();

        return ResponseEntity.ok("New videos saved successfully.");
    }

    @GetMapping("/videos")
    @Operation(
            summary = "유튜브 URL 요청",
            description = "뉴진스 동영상에 대한 유튜브 URL 목록을 응답받을 수 있습니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공", content = @Content(schema = @Schema(implementation = VideoResponse.class)))
            }
    )
    public ResponseEntity<Slice<VideoResponse>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Slice<VideoResponse> videoResponses = videoService.findAll(pageable);

        if (videoResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(videoResponses);
    }
}
