package com.gdsc.frjns.videos.dto;

import lombok.Builder;

@Builder
public record VideoResponse(
        Long id,
        String title,
        String url
) {
}
