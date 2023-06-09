package com.gdsc.frjns.news.domain.model;

import com.gdsc.frjns.news.dto.NewsDTO;
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
    @Column(name = "id")
    private Long id;

    // 스케줄 시작날짜
    @Temporal(TemporalType.DATE)
    @Column(name="start_date")
    private LocalDate startDate;

    // 스케줄 종료날짜
    @Temporal(TemporalType.DATE)
    @Column(name="end_date")
    private LocalDate endDate;

    //자세한 스케줄 내용
    @Column(name = "detail")

    private String detail;

    public NewsDTO toDTO(){
        return NewsDTO.builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .detail(detail)
                .build();
    }
}
