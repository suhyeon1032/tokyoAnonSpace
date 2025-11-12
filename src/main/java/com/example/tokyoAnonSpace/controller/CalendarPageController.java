package com.example.tokyoAnonSpace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarPageController {

    // HTML 페이지 반환
    @GetMapping("/calendar")
    public String showCalendarPage() {
        return "calendar/calendar";
    }
}
