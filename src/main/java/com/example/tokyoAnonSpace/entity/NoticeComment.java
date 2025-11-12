package com.example.tokyoAnonSpace.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "NOTICE_COMMENT")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="notice_id")
    private Notice notice;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String content;
}

