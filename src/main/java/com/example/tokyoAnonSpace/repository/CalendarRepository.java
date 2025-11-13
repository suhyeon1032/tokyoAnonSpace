package com.example.tokyoAnonSpace.repository;

import com.example.tokyoAnonSpace.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByDateBetween(LocalDate start, LocalDate end);

    List<Calendar> findByDate(LocalDate date); // ← 추가

//    Optional<Calendar> findByDate(LocalDate date);
}