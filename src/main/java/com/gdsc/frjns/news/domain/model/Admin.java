package com.gdsc.frjns.news.domain.model;

import com.gdsc.frjns.news.dto.AdminDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name="admin")
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin implements UserDetails {

    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String id;

    //비밀번호
    @Column(nullable = false)
    private String password;

    // 권한 목록, @ElementCollection: 해당 필드가 컬렉션 객체임을 JPA에게 알려줌
    @ElementCollection(fetch = FetchType.EAGER) // 연관된 엔티티 즉시 로딩
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    // 계정의 권한 목록을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    public AdminDTO toDTO(){
        return AdminDTO.builder()
                .id(id)
                .password(password)
                .build();
    }

    @Override
    public String getUsername(){
        return id;
    }

    @Override
    public String getPassword(){
        return password;
    }

    // 계정의 만료 여부 리턴, true: 만료되지 않음
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정의 잠김 여부 리턴, true: 잠기지 않음
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 리턴, true: 만료되지 않음
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부 리턴, true: 활성화
    @Override
    public boolean isEnabled() {
        return true;
    }
}
