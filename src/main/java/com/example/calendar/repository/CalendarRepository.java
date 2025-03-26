package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository {

    CalendarResponseDto saveCalendar(Calendar calendar);

    List<CalendarResponseDto> findAllCalendar();

    Optional<Calendar> findCalendarById(Long id);

    Calendar findCalendarByIdOrElseThrow(Long id);

    int updateCalendar(Long id, String user, String contents);

    int deleteCalendar(Long id);

}