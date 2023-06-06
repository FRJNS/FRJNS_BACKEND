package com.gdsc.frjns.videos.domain.repository;

import com.gdsc.frjns.videos.domain.model.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByChannelId(String channelId);

    Slice<Video> findAllBy(Pageable pageable);
}
