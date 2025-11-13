package com.example.tokyoAnonSpace.service;

import com.example.tokyoAnonSpace.entity.Free;
import com.example.tokyoAnonSpace.repository.FreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FreeService {

    private final FreeRepository freeRepository;

    public List<Free> findAll() {
        return freeRepository.findAll();
    }

    public Free findById(Long id) {
        return freeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    public Free save(Free board) {
        return freeRepository.save(board);
    }

    public void update(Long id, String password, String title, String content) {
        Free board = findById(id);
        if (!board.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        board.setTitle(title);
        board.setContent(content);
    }

    public void delete(Long id, String password) {
        Free board = findById(id);
        if (!board.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        freeRepository.delete(board);
    }

    // 삭제 기능 수정
    public boolean checkPassword(Long id, String password) {
        Free free = findById(id);
        return free.getPassword().equals(password);
    }

    public void like(Long id) {
        Free board = findById(id);
        board.setLikes(board.getLikes() + 1);
    }

    public void dislike(Long id) {
        Free board = findById(id);
        board.setDislikes(board.getDislikes() + 1);
    }

    // 페이징
    public Page<Free> getFreeList(Pageable pageable) {
        return freeRepository.findAll(pageable);
    }

    // main페이지 연동
    public List<Free> getRecentFrees(Pageable pageable) {
        return freeRepository.findAllByOrderByCreatedAtDesc(pageable).getContent();
    }
}
