package com.gdsc.frjns.news.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Schema(description = "스케줄 요청 응답")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewsDTO {
    @Schema(description = "news id", example = "1")
    private Long id;
    @Schema(description = "스케줄 시작 날짜", example = "2023-01-01")
    private Date start_date;
    @Schema(description = "스케줄 종료 날짜", example = "2023-01-03")
    private Date end_date;
    @Schema(description = "스케줄 종료 날짜", example = "OMG 음반 발매")
    private String detail;
}