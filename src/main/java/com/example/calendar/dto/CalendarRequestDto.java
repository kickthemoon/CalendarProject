package com.example.calendar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarRequestDto {
    private String user;
    private String contents;
    private String password;
    private String date;
}
