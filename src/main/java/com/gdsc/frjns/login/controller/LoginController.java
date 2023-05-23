package com.gdsc.frjns.login.controller;

import com.gdsc.frjns.login.domain.dto.LoginDTO;
import com.gdsc.frjns.news.dto.AdminDTO;
import com.gdsc.frjns.news.dto.TokenDTO;
import com.gdsc.frjns.news.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final AdminService adminService;

    // 로그인, id와 pw가 저장된 request body를 자바 객체로 변환하고 로그인 과정에 사용 (잘됨)
    @PostMapping("/login")
    @Operation(
            summary = "Admin 로그인 페이지",
            description = "관리자 계정으로 로그인",
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema=@Schema(implementation = AdminDTO.class)))
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
