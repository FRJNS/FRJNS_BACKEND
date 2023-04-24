package com.gdsc.frjns.mbti.dto;

import lombok.Builder;

@Builder
public record MbtiResponse (
        String sourceMbti,
        String targetName,
        String imageUrl,
        String content
) {
}
