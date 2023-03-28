package com.gdsc.frjns.videos.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "video")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "channel_id", unique = true, nullable = false)
    private String channelId;

    @Column(name = "title", nullable = false, length = 1000)
    private String title;
}
