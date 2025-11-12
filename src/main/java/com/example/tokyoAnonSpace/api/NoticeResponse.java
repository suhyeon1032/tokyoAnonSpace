package com.example.tokyoAnonSpace.api;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResponse {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
