package com.example.tokyoAnonSpace.service;

import com.example.tokyoAnonSpace.entity.NoticeComment;
import com.example.tokyoAnonSpace.repository.NoticeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCommentService {

    private final NoticeCommentRepository noticeCommentRepository;

    public NoticeComment save(NoticeComment noticeComment) {
        return noticeCommentRepository.save(noticeComment);
    }

    public void delete(Long commentId, String password) {
        NoticeComment noticeComment = noticeCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        if (!noticeComment.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        noticeCommentRepository.delete(noticeComment);
    }
}
