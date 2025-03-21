package com.example.calendar.entity;

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

}
