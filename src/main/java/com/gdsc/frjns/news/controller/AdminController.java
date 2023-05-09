package com.gdsc.frjns.news.controller;

import com.gdsc.frjns.news.domain.model.News;
import com.gdsc.frjns.news.dto.NewsDTO;
import com.gdsc.frjns.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminController {
    private final NewsService newsService;
    @PostMapping("/admin/{id}")
    public ResponseEntity<String> addNewsAdmin(@PathVariable("id") Long id, @RequestBody NewsDTO newsRequestDTO) throws Exception {

        if(newsRequestDTO.getId() == null) {
            throw new Exception("id 비어있음");
        }
        if(newsRequestDTO.getStart() == null) {
            throw new Exception("시작 날짜 null");
        }
        if(newsRequestDTO.getEnd() == null) {
            throw new Exception("끝나는 날짜 null");
        }

        //if(!checkDate(new SimpleDateFormat("yyyy-MM-dd").format(fineRequestDto.getDate()))) {
        //    throw new Exception("날짜 포맷 오류" + fineRequestDto.getDate().toString());
        //}
        if(newsRequestDTO.getDetail() == null) {
            throw new Exception("스케쥴 내용 null");
        }
        newsService.addNews(id, newsRequestDTO);
        return ResponseEntity.ok("added schedule successfully");
    }

    @DeleteMapping("/admin/{memberId}")
    public ResponseEntity<String> deleteNews (@PathVariable("id") Long id, @RequestBody NewsDTO newsRequestDTO) {
        if(newsRequestDTO == null) {
            return ResponseEntity.ok("bad request");
        }
        else {
            newsService.deleteNews(newsRequestDTO);
            return ResponseEntity.ok("deleted schedule successfully");
        }
    }
}
