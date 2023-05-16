package com.gdsc.frjns.mbti.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "mbti 궁합 응답")
@Builder
public record MbtiResponse (
        @Schema(description = "요청 mbti", example = "INTP")
        String sourceMbti,
        @Schema(description = "결과 이름", example = "민지(ESTJ)")
        String targetName,
        @Schema(description = "이미지", example = "https://a.img.com/test.png")
        String imageUrl,
        @Schema(description = "결과 내용", example = "관리자 스타일이며 리더에 적합한 민지와 궁합이 좋군요!")
        String content
) {
}
