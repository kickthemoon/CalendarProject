package com.example.calendar.dto;

import com.example.calendar.entity.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalendarResponseDto {
    private Long id;
    private String user;
    private String contents;
    private String password;
    private String date;

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.user = calendar.getUser();
        this.contents = calendar.getContents();
        this.password = calendar.getPassword();
        this.date = calendar.getDate();
    }
}
