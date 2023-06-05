package com.gdsc.frjns.videos.domain.model;

import com.gdsc.frjns.videos.dto.VideoResponse;
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

    public VideoResponse toResponse() {
        return VideoResponse.builder()
                .id(id)
                .title(title)
                .url("https://www.youtube.com/watch?v=" + channelId)
                .build();
    }
}
