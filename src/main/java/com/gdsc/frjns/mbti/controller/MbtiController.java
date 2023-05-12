package com.gdsc.frjns.mbti.controller;

import com.gdsc.frjns.mbti.dto.MbtiResponse;
import com.gdsc.frjns.mbti.service.MbtiService;
import com.gdsc.frjns.news.dto.NewsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MbtiController {
    private final MbtiService mbtiService;

    @GetMapping("/mbti/{sourceMbti}")
    @Operation(
            summary = "mbti 궁합 조회",
            description = "뉴진스 멤버와 나의 MBTI의 궁합을 조회할 수 있습니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "요청 성공", content = @Content(schema = @Schema(implementation = MbtiResponse.class))),
                    @ApiResponse(responseCode = "400", description = "존재하지 않는 mbti 요청", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
            }
    )
    public ResponseEntity<List<MbtiResponse>> findAllByMbti(@PathVariable("sourceMbti") String mbti){
        List<MbtiResponse> responses = mbtiService.findAllBySourceMbti(mbti.toUpperCase());

        if (responses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 MBTI입니다.");
        }

        return ResponseEntity
                .ok(responses);        // 200 OK - 요청 성공
    }
}
