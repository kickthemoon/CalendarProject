package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public CalendarResponseDto saveCalendar(CalendarRequestDto dto) {

        Calendar calendar = new Calendar(dto.getUser(), dto.getContents(), dto.getPassword(), dto.getDate());

        return calendarRepository.saveCalendar(calendar);
    }

    @Override
    public List<CalendarResponseDto> findAllCalendar() {
        return calendarRepository.findAllCalendar();
    }

    @Override
    public CalendarResponseDto findCalendarById(Long id) {

        Calendar calendar = calendarRepository.findCalendarByIdOrElseThrow(id);

        return new CalendarResponseDto(calendar);
    }

    @Transactional
    @Override
    public CalendarResponseDto updateCalendar(Long id, String user, String contents, String password) {

        if (!password.equals(findCalendarById(id).getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호가 틀렸습니다");
        }

        if (user == null || contents == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "입력 값이 없는게 있습니다 확인하세요");
        }

        calendarRepository.updateCalendar(id, user, contents);

        Calendar calendar = calendarRepository.findCalendarByIdOrElseThrow(id);

        return new CalendarResponseDto(calendar);
    }

    @Override
    public void deleteCalendar(Long id) {

        int deleteRow = calendarRepository.deleteCalendar(id);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public String timeNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(formatter);

        return date;
    }
}
