package com.example.calendar.controller;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping
    public List<CalendarResponseDto> findCatalogCalendar() {
        List<CalendarResponseDto> responseList = new ArrayList<>();

        for(Calendar calendar : calendarList.values()) {
            CalendarResponseDto responseDto = new CalendarResponseDto(calendar);
            responseList.add(responseDto);
        }
        return  responseList;
    }

    @GetMapping("/{id}")
    public CalendarResponseDto findCalendarById(@PathVariable Long id) {
        Calendar calendar = calendarList.get(id);

        return new CalendarResponseDto(calendar);
    }

    @PutMapping("/{id}")
    public CalendarResponseDto updateCalendarById(
            @PathVariable Long id,
            @RequestBody CalendarRequestDto dto
    ) {
        Calendar calendar = calendarList.get(id);
        calendar.update(dto);
        return new CalendarResponseDto(calendar);
    }

    @PatchMapping("/{id}")
    public CalendarResponseDto updateContents(
            @PathVariable Long id,
            @RequestBody CalendarRequestDto dto
    ) {
        Calendar calendar = calendarList.get(id);
        calendar.updateContents(dto);
        return new CalendarResponseDto(calendar);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable Long id) {
        calendarList.remove(id);
    }
}
