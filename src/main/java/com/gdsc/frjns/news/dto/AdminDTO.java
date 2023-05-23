package com.gdsc.frjns.news.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "로그인 ")
@Builder
public class AdminDTO {
    @Schema(description = "관리자 아이디")
    private String id;
    @Schema(description = "관리자 비밀번호")
    private String password;
    @Schema(description = "역할(관리자 확인)")
    private String role;
}
