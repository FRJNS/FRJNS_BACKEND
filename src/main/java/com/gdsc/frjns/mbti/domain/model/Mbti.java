package com.gdsc.frjns.mbti.domain.model;

import com.gdsc.frjns.mbti.dto.MbtiResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "mbti")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mbti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "source_mbti", nullable = false)
    private String sourceMbti;

    @Column(name = "target_name", nullable = false)
    private String targetName;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "content", nullable = false)
    private String content;

    public MbtiResponse toResponse() {
        return MbtiResponse.builder()
                .sourceMbti(sourceMbti)
                .targetName(targetName)
                .imageUrl(imageUrl)
                .content(content)
                .build();
    }
}
