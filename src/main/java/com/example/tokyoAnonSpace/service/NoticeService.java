package com.example.tokyoAnonSpace.service;

import com.example.tokyoAnonSpace.entity.Notice;
import com.example.tokyoAnonSpace.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    public Notice findById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public Notice save(Notice notice) {
        return noticeRepository.save(notice);
    }

    public void update(Long id, String password, String title, String content) {
        Notice notice = findById(id);
        if (!notice.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        notice.setTitle(title);
        notice.setContent(content);
        notice.setUpdatedAt(java.time.LocalDateTime.now());
    }

    public void delete(Long id, String password) {
        Notice notice = findById(id);
        if (!notice.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        noticeRepository.delete(notice);
    }

    // 페이징
    public Page<Notice> getNoticeList(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }
}
