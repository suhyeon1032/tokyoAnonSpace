package com.example.tokyoAnonSpace.controller;

import com.example.tokyoAnonSpace.entity.Free;
import com.example.tokyoAnonSpace.entity.FreeComment;
import com.example.tokyoAnonSpace.service.FreeCommentService;
import com.example.tokyoAnonSpace.service.FreeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
    @PostMapping("/{commentId}/delete/check-password")
    @ResponseBody
    public boolean isCheckPassword(@PathVariable Long freeId,
                                                 @PathVariable Long commentId,
                                                 @RequestParam String password) {
        return commentService.checkPassword(commentId, password);
    }

    // 실제 삭제 (폼 제출용)
    @PostMapping("/{commentId}/delete")
    public String delete(@PathVariable Long freeId,
                                       @PathVariable Long commentId,
                                       @RequestParam String password) {
        commentService.delete(commentId, password);
//        if (deleted) {
//            // 삭제 성공
//            return ResponseEntity.ok().build();
//        } else {
//            // 틀린 비밀번호 → HTTP 403 Forbidden 반환
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }

        return "redirect:/free/" + freeId;
    }

}
