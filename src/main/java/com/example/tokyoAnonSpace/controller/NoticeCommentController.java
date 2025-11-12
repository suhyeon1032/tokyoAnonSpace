package com.example.tokyoAnonSpace.controller;

import com.example.tokyoAnonSpace.entity.Notice;
import com.example.tokyoAnonSpace.entity.NoticeComment;
import com.example.tokyoAnonSpace.service.NoticeCommentService;
import com.example.tokyoAnonSpace.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice/{noticeId}/comment")
public class NoticeCommentController {

    private final NoticeCommentService noticeCommentService;
    private final NoticeService noticeService;

    // 댓글 등록
    @PostMapping("/write")
    public String write(@PathVariable Long noticeId,
                        @RequestParam String nickname,
                        @RequestParam String password,
                        @RequestParam String content) {
        Notice notice = noticeService.findById(noticeId);
        NoticeComment noticeComment = NoticeComment.builder()
                .notice(notice)
                .nickname(nickname)
                .password(password)
                .content(content)
                .build();
        noticeCommentService.save(noticeComment);
        return "redirect:/notice/" + noticeId;
    }

    // 댓글 삭제
    @PostMapping("/{commentId}/delete")
    public String delete(@PathVariable Long noticeId,
                         @PathVariable Long commentId,
                         @RequestParam String password) {
        noticeCommentService.delete(commentId, password);
        return "redirect:/notice/" + noticeId;
    }

}
