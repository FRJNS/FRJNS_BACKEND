package com.gdsc.frjns.login.controller;

import com.gdsc.frjns.login.domain.dto.LoginDTO;
import com.gdsc.frjns.news.dto.TokenDTO;
import com.gdsc.frjns.news.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final AdminService adminService;

    //잘됨
//    @GetMapping("/login")
//    public ResponseEntity<String> main() {
//        return ResponseEntity.ok("메인 로그인 페이지");
//    }

    // 로그인, id와 pw가 저장된 request body를 자바 객체로 변환하고 로그인 과정에 사용 (잘됨)
    @PostMapping("/login")
    // @RequestBody: HTTP Request Body 내용을 통째로 자바 객체로 변환해서 매핑된 메소드 파라미터로 전달
    public TokenDTO login(@RequestBody LoginDTO adminLoginRequestDto) {
        String id = adminLoginRequestDto.getId();
        String password = adminLoginRequestDto.getPassword();
        TokenDTO tokenDTO = adminService.login(id, password);
        return tokenDTO;
    }

    // 로그인 시 권한에 해당하는 화면으로 리다이렉션
    @GetMapping("/login")
    public RedirectView loginRedirect(Authentication authentication) throws Exception{
        RedirectView redirectView = new RedirectView();
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN")) {
            redirectView.setUrl("/api/admin");
            return redirectView;
        }
        else {
            throw new Exception("권한 오류");
        }
    }
}
