package com.example.tokyoAnonSpace.repository;

import com.example.tokyoAnonSpace.entity.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> {}
