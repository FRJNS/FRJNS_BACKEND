package com.gdsc.frjns.news.service;

import com.gdsc.frjns.news.domain.model.News;
import com.gdsc.frjns.news.domain.repository.NewsRepository;
import com.gdsc.frjns.news.dto.NewsDTO;
import com.gdsc.frjns.news.dto.NewsRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;


    // Slice로 전체 스케줄 불러오기
    public Slice<NewsDTO> findAll(Pageable pageable){
        Slice<News> slice = newsRepository.findAllBy(pageable);
        return slice.map(News::toDTO);
    }

    // 전체 스케줄 불러오기
    public List<News> findAll() {
        List<News> newsList = newsRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));

        return newsList;
    }

    //스케쥴 추가
    public News addNews(NewsRequestDTO newsRequestDTO){
        return newsRepository.save(newsRequestDTO.toEntity());
    }

    //스케쥴 삭제
    public void deleteNews(Long id) {
        News deleteNews = newsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 id의 스케줄이 존재하지 않습니다."));
        newsRepository.deleteById(deleteNews.getId());
    }
}
