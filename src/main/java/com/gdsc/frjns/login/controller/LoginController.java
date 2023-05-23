package com.gdsc.frjns.login.controller;

import com.gdsc.frjns.login.domain.dto.LoginDTO;
import com.gdsc.frjns.news.domain.model.Admin;
import com.gdsc.frjns.news.dto.TokenDTO;
import com.gdsc.frjns.news.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final AdminService adminService;

    // 로그인, id와 pw가 저장된 request body를 자바 객체로 변환하고 로그인 과정에 사용
    @PostMapping("/login")
    @Operation(
            summary = "로그인 페이지",
            description = "관리자 계정으로 로그인하는 페이지입니다.",
            responses={
                    @ApiResponse(responseCode = "200", description = "로그인 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음")
    }
    )
    // @RequestBody: HTTP Request Body 내용을 통째로 자바 객체로 변환해서 매핑된 메소드 파라미터로 전달
    public TokenDTO login(@RequestBody LoginDTO adminLoginRequestDto) {
        String id = adminLoginRequestDto.getId();
        String password = adminLoginRequestDto.getPassword();
        TokenDTO tokenDTO = adminService.login(id, password);
        return tokenDTO;
    }

}
