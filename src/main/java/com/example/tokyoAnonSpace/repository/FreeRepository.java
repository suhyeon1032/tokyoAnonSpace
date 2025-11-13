package com.example.tokyoAnonSpace.repository;

import com.example.tokyoAnonSpace.entity.Free;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;

@Repository
public interface FreeRepository extends JpaRepository<Free, Long> {
    Page<Free> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
