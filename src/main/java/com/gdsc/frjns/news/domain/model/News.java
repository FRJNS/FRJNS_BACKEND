package com.gdsc.frjns.news.domain.model;

import com.gdsc.frjns.news.dto.NewsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Getter
@Entity
@Table(name="news")
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class News {

    // 아이디 자동 생성 (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,unique = true,nullable = false)
    private Long id;

    // 스케줄 시작날짜
    @Temporal(TemporalType.DATE)
    @Column
    private Date start;

    // 스케줄 종료날짜
    @Temporal(TemporalType.DATE)
    @Column
    private Date end;

    //자세한 스케줄 내용
    @Column(nullable = false)
    private String detail;

    public NewsDTO toDTO(){
        return NewsDTO.builder()
                .id(id)
                .start(start)
                .end(end)
                .detail(detail)
                .build();
    }
}