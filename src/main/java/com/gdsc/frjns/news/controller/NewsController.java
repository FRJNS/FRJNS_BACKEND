package com.gdsc.frjns.news.controller;

import com.gdsc.frjns.common.annotation.CustomPageableAsQueryParam;
import com.gdsc.frjns.mbti.dto.MbtiResponse;
import com.gdsc.frjns.news.dto.NewsDTO;
import com.gdsc.frjns.news.dto.NewsResponse;
import com.gdsc.frjns.news.service.NewsService;
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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    @Operation(
            summary = "스케줄 조회",
            description = "뉴진스의 스케줄을 조회할 수 있습니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공", content = @Content(schema = @Schema(implementation = NewsDTO.class)))
            }
    )
    @CustomPageableAsQueryParam
    public ResponseEntity<Slice<NewsResponse>> newsList(@PageableDefault(size=5)Pageable pageable) {
        Slice<NewsResponse> newsDTOS = newsService.findAll(pageable);

        if (newsDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(newsDTOS);
    }
}
