package com.example.calendar.dto;

import com.example.calendar.entity.Calendar;
import lombok.Getter;

@Getter
public class CalendarResponseDto {
    private Long id;
    private Long month;
    private Long day;
    private String title;
    private String contents;

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.month = calendar.getMonth();
        this.day = calendar.getDay();
        this.title = calendar.getTitle();
        this.contents = calendar.getContents();
    }
}
