package com.gdsc.frjns.news.controller;

import com.gdsc.frjns.news.domain.model.News;
import com.gdsc.frjns.news.domain.repository.AdminRepository;
import com.gdsc.frjns.news.domain.repository.NewsRepository;
import com.gdsc.frjns.news.dto.NewsDTO;
import com.gdsc.frjns.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final NewsService newsService;
    private final NewsRepository newsRepository;

    @GetMapping("/admin")
    public ResponseEntity<List<NewsDTO>> admin(){
        List<News> list = newsRepository.findAll();
        return ResponseEntity.ok(list.stream()
                .map(News::toDTO)
                .collect(Collectors.toList()));
    }
    @PostMapping("/admin")
    public ResponseEntity<String> addNewsAdmin(@RequestBody NewsDTO newsRequestDTO) throws Exception {

        if(newsRequestDTO.getId() == null) {
            throw new Exception("id 비어있음");
        }
        if(newsRequestDTO.getStart() == null) {
            throw new Exception("시작 날짜 null");
        }
        if(newsRequestDTO.getEnd() == null) {
            throw new Exception("끝나는 날짜 null");
        }

        if(newsRequestDTO.getDetail() == null) {
            throw new Exception("스케줄 내용 null");
        }
        newsService.addNews(newsRequestDTO);
        return ResponseEntity.ok("added schedule successfully");
    }

    @DeleteMapping("/admin")
    public ResponseEntity<String> deleteNews (@RequestBody NewsDTO newsRequestDTO) {
        if(newsRequestDTO == null) {
            return ResponseEntity.ok("없는 스케줄입니다.");
        }
        else {
            newsService.deleteNews(newsRequestDTO);
            return ResponseEntity.ok("deleted schedule successfully");
        }
    }
}
