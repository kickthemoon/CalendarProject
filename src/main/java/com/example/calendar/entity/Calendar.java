package com.example.calendar.entity;

import com.example.calendar.dto.CalendarRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Calendar {

    private Long id;
    private Long month;
    private Long day;
    private String title;
    private String contents;

    public void update(CalendarRequestDto dto) {
        this.month = dto.getMonth();
        this.day = dto.getDay();
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public void updateContents(CalendarRequestDto dto) {
        this.contents = getContents();
    }

}
