package com.example.tokyoAnonSpace.repository;

import com.example.tokyoAnonSpace.entity.Free;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeRepository extends JpaRepository<Free, Long> {}
