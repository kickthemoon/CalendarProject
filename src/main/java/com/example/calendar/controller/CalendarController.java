package com.example.calendar.controller;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/calendars")
public class CalendarController {
    private final Map<Long, Calendar> calendarList =new HashMap<>();

    @PostMapping
    public CalendarResponseDto createCalendar(@RequestBody CalendarRequestDto dto) {
        long calendarId = calendarList.isEmpty() ? 1 : Collections.max(calendarList.keySet()) + 1;

        Calendar calendar = new Calendar(calendarId, dto.getMonth(), dto.getDay(), dto.getTitle(), dto.getContents());

        calendarList.put(calendarId, calendar);

        return new CalendarResponseDto(calendar);
    }
}
