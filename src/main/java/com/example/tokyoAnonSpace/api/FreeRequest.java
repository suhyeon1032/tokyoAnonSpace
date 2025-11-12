package com.example.tokyoAnonSpace.api;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreeRequest {
    private String nickname;
    private String password;
    private String title;
    private String content;
}
