package com.example.tokyoAnonSpace.repository;

import com.example.tokyoAnonSpace.entity.FreeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeCommentRepository extends JpaRepository<FreeComment, Long> {}
