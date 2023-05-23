package com.gdsc.frjns.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenDTO {
    private String grantType; // 인증을 받는 유형 (Bearer 등)
    private String accessToken; // 요청에 대한 다양한 정보를 담고 실질적 인증 역할
}
