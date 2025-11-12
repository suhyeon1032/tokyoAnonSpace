package com.example.tokyoAnonSpace.controller;

import com.example.tokyoAnonSpace.entity.Free;
import com.example.tokyoAnonSpace.entity.FreeComment;
import com.example.tokyoAnonSpace.service.FreeCommentService;
import com.example.tokyoAnonSpace.service.FreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/free/{freeId}/freeComment")
public class FreeCommentController {

    private final FreeCommentService commentService;
    private final FreeService freeService;

    // 댓글 등록
    @PostMapping("/write")
    public String write(@PathVariable Long freeId,
                        @RequestParam String nickname,
                        @RequestParam String password,
                        @RequestParam String content) {
        Free free = freeService.findById(freeId);
        FreeComment comment = FreeComment.builder()
                .board(free)
                .nickname(nickname)
                .password(password)
                .content(content)
                .build();
        commentService.save(comment);
        return "redirect:/free/" + freeId;
    }

    // 댓글 삭제
    @PostMapping("/{commentId}/delete")
    public String delete(@PathVariable Long freeId,
                         @PathVariable Long commentId,
                         @RequestParam String password) {
        commentService.delete(commentId, password);
        return "redirect:/free/" + freeId;
    }
}
