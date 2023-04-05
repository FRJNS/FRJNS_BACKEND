package com.gdsc.frjns.news.controller;

import com.gdsc.frjns.news.service.NewsService;
import com.gdsc.frjns.news.dto.NewsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/api/news")
    public ResponseEntity<Slice<NewsDTO>> newsList(@PageableDefault(size=5)Pageable pageable) {
        Slice<NewsDTO> newsDTOS = newsService.findAll(pageable);
        return ResponseEntity.ok(newsDTOS);
    }
}