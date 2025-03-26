package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;

import java.util.List;

public interface CalendarService {

    CalendarResponseDto saveCalendar(CalendarRequestDto dto);

    List<CalendarResponseDto> findAllCalendar();

    CalendarResponseDto findCalendarById(Long id);

    CalendarResponseDto updateCalendar(Long id, String user, String contents, String password);

    void deleteCalendar(Long id);
}
