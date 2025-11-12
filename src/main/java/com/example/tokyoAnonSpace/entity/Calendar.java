package com.example.tokyoAnonSpace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "CALENDAR")
@Getter
@Setter
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CAL_DATE", nullable = false)
    private LocalDate date; // 날짜 필드

    @Column(nullable = false)
    private String content; // 일정 내용
}
