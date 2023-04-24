package com.gdsc.frjns.mbti.controller;

import com.gdsc.frjns.mbti.dto.MbtiResponse;
import com.gdsc.frjns.mbti.service.MbtiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MbtiController {
    private final MbtiService mbtiService;

    @GetMapping("/mbti/{sourceMbti}")
    public ResponseEntity<List<MbtiResponse>> findAllByMbti(@PathVariable("sourceMbti") String mbti){
        List<MbtiResponse> responses = mbtiService.findAllBySourceMbti(mbti.toUpperCase());

        if (responses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 MBTI입니다.");
        }

        return ResponseEntity
                .ok(responses);        // 200 OK - 요청 성공
    }
}
