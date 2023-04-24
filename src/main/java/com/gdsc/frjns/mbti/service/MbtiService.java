package com.gdsc.frjns.mbti.service;

import com.gdsc.frjns.mbti.domain.model.Mbti;
import com.gdsc.frjns.mbti.domain.repository.MbtiRepository;
import com.gdsc.frjns.mbti.dto.MbtiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MbtiService {
    private final MbtiRepository mbtiRepository;

    @Transactional(readOnly = true)
    public List<MbtiResponse> findAllBySourceMbti(String mbti) {
        List<Mbti> members = mbtiRepository.findAllBySourceMbti(mbti);

        return members.stream()
                .map(Mbti::toResponse)
                .collect(Collectors.toList());
    }
}
