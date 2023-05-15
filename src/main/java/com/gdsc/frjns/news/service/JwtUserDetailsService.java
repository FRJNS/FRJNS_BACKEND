package com.gdsc.frjns.news.service;

import com.gdsc.frjns.news.domain.model.Admin;
import com.gdsc.frjns.news.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// UserDetailsService 인터페이스를 구현한 클래스
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    // 전달받은 username 값을 통해 memberRepository에서 유저를 찾아서 UserDetails 객체로 리턴
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return adminRepository.findById(id)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // DB에 해당하는 User 값이 존재한다면 UserDetails 객체로 리턴
    private UserDetails createUserDetails(Admin admin) {
        UserDetails build = User.builder()
                .username(admin.getUsername())
                .password(passwordEncoder.encode(admin.getPassword()))
                .roles(admin.getRoles().toArray(new String[0]))
                .build();
        return build;
    }
}
