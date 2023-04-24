package com.gdsc.frjns.news.domain.model;

import com.gdsc.frjns.news.dto.NewsResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "news")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class News {

    // 아이디 자동 생성 (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // 스케줄 시작날짜
    @Column(name = "start_date")
    private LocalDate startDate;

    // 스케줄 종료날짜
    @Column(name = "end_date")
    private LocalDate endDate;

    //자세한 스케줄 내용
    @Column(name = "detail", nullable = false)
    private String detail;

    public NewsResponse toResponse(){
        return NewsResponse.builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .detail(detail)
                .build();
    }
}
