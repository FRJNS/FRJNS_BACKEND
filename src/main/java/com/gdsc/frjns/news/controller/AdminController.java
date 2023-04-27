package com.gdsc.frjns.news.controller;

import com.gdsc.frjns.news.domain.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminController {
    @PostMapping("/admin/{id}")
    public ResponseEntity<String> addNewsAdmin(@PathVariable("id") Long id, @RequestBody NewsResponse newsResponse) throws Exception {

        if(id.isEmpty()) {
            throw new Exception("id 비어있음");
        }
        if(newsResponse.getStart() == null) {
            throw new Exception("시작 날짜 null");
        }
        if(newsResponse.getEnd() == null) {
            throw new Exception("끝나는 날짜 null");
        }

        //if(!checkDate(new SimpleDateFormat("yyyy-MM-dd").format(fineRequestDto.getDate()))) {
        //    throw new Exception("날짜 포맷 오류" + fineRequestDto.getDate().toString());
        //}
        if(newsResponse.getDetail() == null) {
            throw new Exception("스케쥴 내용 null");
        }
        newsService.addNews(id, newsResponse);
        return ResponseEntity.ok("added schedule successfully");
    }

    @DeleteMapping("/admin/{memberId}")
    public ResponseEntity<String> deleteNews (@PathVariable("id") Long id, @RequestBody NewsResponse newsResponse) {
        if(newsResponse == null) {
            return ResponseEntity.ok("bad request");
        }
        else {
            newsService.deleteNews(NewsDTO);
            return ResponseEntity.ok("deleted schedule successfully");
        }
    }
}
