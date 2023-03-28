package com.gdsc.frjns.mbti.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
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
}
