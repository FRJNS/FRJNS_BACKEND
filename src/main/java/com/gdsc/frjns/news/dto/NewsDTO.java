package com.gdsc.frjns.news.dto;

import com.gdsc.frjns.news.domain.model.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewsDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String detail;

    public News toEntity(){
        return News.builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .detail(detail)
                .build();
    }
}