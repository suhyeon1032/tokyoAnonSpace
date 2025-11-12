package com.example.tokyoAnonSpace.controller;

import com.example.tokyoAnonSpace.entity.Calendar;
import com.example.tokyoAnonSpace.service.CalendarService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // 월별 일정 조회 (AJAX용)
    @GetMapping("/{year}/{month}")
    public List<Calendar> getCalendar(@PathVariable int year, @PathVariable int month) {
        return calendarService.getEvents(year, month);
    }

    @PostMapping("/add")
    public void addEvent(@RequestBody Map<String, String> data) {
        String dateStr = data.get("date");
        String content = data.get("content");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // content가 비었으면 해당 날짜 일정 삭제
        if (content == null || content.trim().isEmpty()) {
            calendarService.deleteEventByDate(date);
            return;
        }

        calendarService.saveOrUpdateEvent(date, content);
    }
    @GetMapping("/day/{date}")
    public List<Calendar> getEventsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return calendarService.getEventsByDate(localDate);
    }
}