package com.example.tokyoAnonSpace.controller;

import com.example.tokyoAnonSpace.api.NoticeRequest;
import com.example.tokyoAnonSpace.api.NoticeResponse;
import com.example.tokyoAnonSpace.entity.Notice;
import com.example.tokyoAnonSpace.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    // 게시글 목록
    @GetMapping
    public String list(Model model) {
        List<NoticeResponse> reviews = noticeService.findAll().stream()
                .map(r -> new NoticeResponse(r.getId(), r.getNickname(), r.getTitle(), r.getContent(), r.getCreatedAt(), r.getUpdatedAt()))
                .collect(Collectors.toList());
        model.addAttribute("reviews", reviews);
        return "notice/list";
    }

    // 글 작성 화면
    @GetMapping("/write")
    public String writeForm() {
        return "notice/write";
    }

    // 글 등록
    @PostMapping("/write")
    public String writeSubmit(@ModelAttribute NoticeRequest request) {
        Notice notice = Notice.builder()
                .nickname(request.getNickname())
                .password(request.getPassword())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        noticeService.save(notice);
        return "redirect:/notice";
    }

    // 글 상세
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Notice r = noticeService.findById(id);
        model.addAttribute("notice", r);
        return "notice/view";
    }

    // 글 수정 화면
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Notice r = noticeService.findById(id);
        model.addAttribute("notice", r);
        return "notice/edit";
    }

    // 글 수정 처리
    @PostMapping("/{id}/edit")
    public String editSubmit(@PathVariable Long id,
                             @RequestParam String password,
                             @ModelAttribute NoticeRequest request) {
        noticeService.update(id, password, request.getTitle(), request.getContent());
        return "redirect:/notice/" + id;
    }

    // 글 삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @RequestParam String password) {
        noticeService.delete(id, password);
        return "redirect:/notice";
    }
}
