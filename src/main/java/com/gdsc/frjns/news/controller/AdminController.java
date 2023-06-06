package com.gdsc.frjns.news.controller;

import com.gdsc.frjns.news.domain.model.News;
import com.gdsc.frjns.news.domain.repository.AdminRepository;
import com.gdsc.frjns.news.domain.repository.NewsRepository;
import com.gdsc.frjns.news.dto.NewsDTO;
import com.gdsc.frjns.news.dto.NewsRequestDTO;
import com.gdsc.frjns.news.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final NewsService newsService;

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
        List<News> list = newsService.findAll();
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
                    @ApiResponse(responseCode = "400", description = "잘못된 요청")
            }
    )
    public ResponseEntity<String> addNewsAdmin(@RequestBody NewsRequestDTO newsRequestDTO) {
        try {
            newsService.addNews(newsRequestDTO);
            return ResponseEntity.ok("added schedule successfully");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(("잘못된 요청입니다."));
        }
    }

    @DeleteMapping("/admin/{id}")
    @Operation(
            summary = "스케줄 삭제",
            description = "뉴스 id로 스케줄 삭제하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "스케줄 삭제 성공", content= @Content(schema=@Schema(implementation = NewsDTO.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 id"),
                    @ApiResponse(responseCode = "404", description = "존재하지 않는 id")
            }
    )
    public ResponseEntity<String> deleteNews (@PathVariable("id") Long id) throws IllegalArgumentException {
        try {
            newsService.deleteNews(id);
            return ResponseEntity.ok("스케줄 삭제 완료");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(("잘못된 요청입니다."));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
