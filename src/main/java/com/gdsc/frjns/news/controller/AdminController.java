package com.gdsc.frjns.news.controller;

import com.gdsc.frjns.news.domain.model.News;
import com.gdsc.frjns.news.domain.repository.AdminRepository;
import com.gdsc.frjns.news.domain.repository.NewsRepository;
import com.gdsc.frjns.news.dto.NewsDTO;
import com.gdsc.frjns.news.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "스케쥴 전체 조회",
            description = "관리자는 뉴진스의 모든 스케쥴을 한 번에 확인 할 수 있습니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음")
            }
    )
    public ResponseEntity<List<NewsDTO>> admin(){
        List<News> list = newsRepository.findAll();
        return ResponseEntity.ok(list.stream()
                .map(News::toDTO)
                .collect(Collectors.toList()));
    }
    @PostMapping("/admin")
    @Operation(
            summary = "스케줄 추가",
            description = "스케줄 추가하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "스케줄 추가 성공", content= @Content(schema=@Schema(implementation = NewsDTO.class))),
                    @ApiResponse(responseCode = "403", description = "권한 없음")
            }
    )
    public ResponseEntity<String> addNewsAdmin(@RequestBody NewsDTO newsRequestDTO) throws Exception {
        newsService.addNews(newsRequestDTO);
        return ResponseEntity.ok("added schedule successfully");
    }

    @DeleteMapping("/admin/{id}")
    @Operation(
            summary = "스케줄 삭제",
            description = "뉴스 id로 스케줄 삭제하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "스케줄 삭제 성공", content= @Content(schema=@Schema(implementation = NewsDTO.class))),
                    @ApiResponse(responseCode = "403", description = "권한 없음")
            }
    )
    public ResponseEntity<String> deleteNews (@PathVariable("id") Long id) {
        if(newsRepository.findById(id) == null) {
            return ResponseEntity.ok("없는 스케줄입니다.");
        }
        else {
            newsService.deleteNews(id);
            return ResponseEntity.ok("스케줄 삭제 완료");
        }
    }
}
