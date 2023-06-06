package com.gdsc.frjns.videos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "mbti 궁합 응답")
@Builder
public record VideoResponse(
        @Schema(description = "video id", example = "12")
        Long id,
        @Schema(description = "video 제목", example = "[릴레이댄스] NewJeans(뉴진스) - Hype Boy (4K)")
        String title,
        @Schema(description = "video url", example = "https://www.youtube.com/watch?v=NcJo-T5Wo3U")
        String url
) {
}
