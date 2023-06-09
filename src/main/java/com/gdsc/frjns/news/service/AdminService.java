package com.gdsc.frjns.news.service;

import com.gdsc.frjns.jwt.TokenProvider;
import com.gdsc.frjns.news.domain.model.Admin;
import com.gdsc.frjns.news.domain.repository.AdminRepository;
import com.gdsc.frjns.news.dto.AdminDTO;
import com.gdsc.frjns.news.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDTO login(String id, String password){
        // 1. ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 객체는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);

        // 2. 실제 검증(사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매소드가 실행될 때 JwtUserDetailsService에서 만든 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증된 정보를 기반으로 JWT 토큰 생성 후 리턴
        TokenDTO tokenDTO = tokenProvider.createToken(authentication);
        return tokenDTO;
    }
}
