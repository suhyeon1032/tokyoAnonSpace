package com.example.tokyoAnonSpace.repository;

import com.example.tokyoAnonSpace.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findAll(Pageable pageable);

    Page<Notice> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
