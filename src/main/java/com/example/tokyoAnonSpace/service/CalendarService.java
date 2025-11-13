package com.example.tokyoAnonSpace.service;

import com.example.tokyoAnonSpace.dto.CalendarDto;
import com.example.tokyoAnonSpace.entity.Calendar;
import com.example.tokyoAnonSpace.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public List<Calendar> getEvents(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return calendarRepository.findByDateBetween(start, end);
    }


    public void saveEvent(LocalDate date, String content) {
        List<Calendar> existing = calendarRepository.findByDate(date);
        if (!existing.isEmpty()) {
            Calendar event = existing.get(0);
            event.setContent(content);
            calendarRepository.save(event);
        } else {
            Calendar event = new Calendar();
            event.setDate(date);
            event.setContent(content);
            calendarRepository.save(event);
        }
    }

    // 일정 저장 또는 수정
    public void saveOrUpdateEvent(LocalDate date, String content) {
        List<Calendar> existing = calendarRepository.findByDate(date);
        if (!existing.isEmpty()) {
            Calendar event = existing.get(0);
            event.setContent(content);
            calendarRepository.save(event);
        } else {
            Calendar event = new Calendar();
            event.setDate(date);
            event.setContent(content);
            calendarRepository.save(event);
        }
    }

    // 일정 삭제 (빈칸 저장 시 호출)
    public void deleteEventByDate(LocalDate date) {
        List<Calendar> events = calendarRepository.findByDate(date);
        if (!events.isEmpty()) {
            calendarRepository.deleteAll(events);
        }
    }

    public List<Calendar> getEventsByDate(LocalDate date) {
        return calendarRepository.findByDate(date);
    }

}
