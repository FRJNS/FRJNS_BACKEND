package com.gdsc.frjns.news.domain.repository;

import com.gdsc.frjns.news.domain.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(String id);
}
