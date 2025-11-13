package com.example.tokyoAnonSpace.service;

import com.example.tokyoAnonSpace.entity.Free;
import com.example.tokyoAnonSpace.entity.FreeComment;
import com.example.tokyoAnonSpace.repository.FreeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FreeCommentService {

    private final FreeCommentRepository commentRepository;

    public FreeComment save(FreeComment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Long commentId, String password) {
        FreeComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        boolean ifcomment = checkPassword(commentId,password);
        if (!ifcomment) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }else{
            commentRepository.delete(comment);
        }
    }

    // find ID
    public FreeComment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    // 삭제 기능 수정
    public boolean checkPassword(Long commentId, String password) {
        FreeComment comment = findById(commentId);
        return comment.getPassword().equals(password);
    }
}
