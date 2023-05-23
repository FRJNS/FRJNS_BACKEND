package com.gdsc.frjns.news.service;

import com.gdsc.frjns.news.domain.model.News;
import com.gdsc.frjns.news.domain.repository.NewsRepository;
import com.gdsc.frjns.news.dto.NewsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;


    //전체 스케줄 불러오기
    public Slice<NewsDTO> findAll(Pageable pageable){
        Slice<News> slice = newsRepository.findAllBy(pageable);
        return slice.map(News::toDTO);
    }

    //스케쥴 추가
    public News addNews(NewsDTO newsRequestDTO){
        return newsRepository.save(newsRequestDTO.toEntity());
    }

    //스케쥴 삭제
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
