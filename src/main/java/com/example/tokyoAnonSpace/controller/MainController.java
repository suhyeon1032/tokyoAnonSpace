package com.example.tokyoAnonSpace.controller;

import com.example.tokyoAnonSpace.entity.Calendar;
import com.example.tokyoAnonSpace.entity.Free;
import com.example.tokyoAnonSpace.entity.Notice;
import com.example.tokyoAnonSpace.service.CalendarService;
import com.example.tokyoAnonSpace.service.FreeService;
import com.example.tokyoAnonSpace.service.NoticeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    private final FreeService freeService;
    private final NoticeService noticeService;
    private final CalendarService calService;

    public MainController(FreeService freeService, NoticeService noticeService, CalendarService calService) {
        this.freeService = freeService;
        this.noticeService = noticeService;
        this.calService = calService;
    }

    @GetMapping("/main")
    public String mainView(Model model) {
        // 공지 최근 3개
        List<Notice> noticeList = noticeService.getRecentNotices(PageRequest.of(0, 3));
        model.addAttribute("noticeList", noticeList);
//        return "/index/main";

        // 자유게시판 최근 3개
        List<Free> freeList = freeService.getRecentFrees(PageRequest.of(0, 3));
        model.addAttribute("freeList", freeList);

        // 오늘 일정 (없으면 null)
        LocalDate today = LocalDate.now();
        List<Calendar> todaySchedules = calService.getEventsByDate(today);

        // null 방지
        if (todaySchedules == null) {
            todaySchedules = List.of();
        }

        model.addAttribute("todaySchedule", todaySchedules);
        model.addAttribute("today", today);


        return "index/main";
    }
}
