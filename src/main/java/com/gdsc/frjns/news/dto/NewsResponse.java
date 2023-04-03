package com.gdsc.frjns.news.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record NewsResponse(
        Long id,
        LocalDate startDate,
        LocalDate endDate,
        String detail
) {
}
