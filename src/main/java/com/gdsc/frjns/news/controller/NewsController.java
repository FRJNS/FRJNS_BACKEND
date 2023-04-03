package com.gdsc.frjns.news.controller;

import com.gdsc.frjns.news.dto.NewsResponse;
import com.gdsc.frjns.news.service.NewsService;
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
    public ResponseEntity<Slice<NewsResponse>> newsList(@PageableDefault(size=5)Pageable pageable) {
        Slice<NewsResponse> newsDTOS = newsService.findAll(pageable);

        if (newsDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(newsDTOS);
    }
}
