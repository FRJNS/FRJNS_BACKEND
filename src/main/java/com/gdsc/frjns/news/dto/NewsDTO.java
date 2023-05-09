package com.gdsc.frjns.news.dto;

import com.gdsc.frjns.news.domain.model.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewsDTO {
    private Long id;
    private Date start;
    private Date end;
    private String detail;

    public News toEntity(){
        return News.builder()
                .id(id)
                .start(start)
                .end(end)
                .detail(detail)
                .build();
    }
}