package com.gdsc.frjns.news.domain.repository;

import com.gdsc.frjns.news.domain.model.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Slice<News> findAllBy(Pageable pageable);
}
