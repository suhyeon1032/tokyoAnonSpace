package com.example.tokyoAnonSpace.controller;

import com.example.tokyoAnonSpace.entity.Free;
import com.example.tokyoAnonSpace.api.FreeRequest;
import com.example.tokyoAnonSpace.service.FreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/free")
public class FreeController {

    private final FreeService freeService;

    @GetMapping
    public String list(Model model) {
        List<Free> boards = freeService.findAll();
        model.addAttribute("boards", boards);
        return "free/list";
    }

    @GetMapping("/write")
    public String writeForm() {
        return "free/write";
    }

    @PostMapping("/write")
    public String writeSubmit(@ModelAttribute FreeRequest request) {
        Free board = Free.builder()
                .nickname(request.getNickname())
                .password(request.getPassword())
                .title(request.getTitle())
                .content(request.getContent())
                .likes(0)
                .dislikes(0)
                .build();
        freeService.save(board);
        return "redirect:/free";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Free board = freeService.findById(id);
        model.addAttribute("board", board);
        return "free/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Free board = freeService.findById(id);
        model.addAttribute("board", board);
        return "free/edit";
    }

    @PostMapping("/{id}/edit")
    public String editSubmit(@PathVariable Long id,
                             @RequestParam String password,
                             @ModelAttribute FreeRequest request) {
        freeService.update(id, password, request.getTitle(), request.getContent());
        return "redirect:/free/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @RequestParam String password) {
        freeService.delete(id, password);
        return "redirect:/free";
    }

    @PostMapping("/{id}/like")
    public String like(@PathVariable Long id) {
        freeService.like(id);
        return "redirect:/free/" + id;
    }

    @PostMapping("/{id}/dislike")
    public String dislike(@PathVariable Long id) {
        freeService.dislike(id);
        return "redirect:/free/" + id;
    }
}
