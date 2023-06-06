package com.gdsc.frjns.news.dto;

import com.gdsc.frjns.news.domain.model.News;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Schema(description = "스케줄 추가")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewsRequestDTO {
    @Schema(description = "스케줄 시작 날짜", example = "2023-01-01")
    private LocalDate startDate;

    @Schema(description = "스케줄 종료 날짜", example = "2023-01-03")
    private LocalDate endDate;

    @Schema(description = "스케줄 상세", example = "OMG 음반 발매")
    private String detail;


    public News toEntity(){
        return News.builder()
                .startDate(startDate)
                .endDate(endDate)
                .detail(detail)
                .build();
    }
}
