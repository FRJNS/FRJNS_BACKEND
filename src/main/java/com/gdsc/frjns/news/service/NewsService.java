package com.gdsc.frjns.news.service;

import com.gdsc.frjns.news.dto.NewsDTO;
import com.gdsc.frjns.news.domain.model.News;
import com.gdsc.frjns.news.domain.repository.NewsRepository;
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
       /* return newsRepository.findAll().stream()
                .map(News::toDTO)
                .collect(Collectors.toList());
        */
    }

    //스케쥴 추가
    public void addNews(Long id, NewsDTO newsRequestDTO){
        newsRequestDTO.setDetail(String.valueOf(newsRepository.findById(id).get()));
        newsRepository.save(newsRequestDTO.toEntity());
    }

    //스케쥴 삭제
    // 벌금 내역 삭제
    public void deleteNews(NewsDTO newsRequestDTO) {
        News news = newsRepository.findById(newsRequestDTO.getId()).get();
        newsRepository.delete(news);
    }


}