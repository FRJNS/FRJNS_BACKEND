package com.gdsc.frjns.login.domain.dto;

import lombok.Data;

// DTO(Data Transfer Object): 데이터의 전송을 담당하는 객체
@Data
public class LoginDTO {
    private String id;
    private String password;
}
