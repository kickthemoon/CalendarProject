package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class CalendarRequestDto {
    private Long month;
    private Long day;
    private String title;
    private String contents;
}
