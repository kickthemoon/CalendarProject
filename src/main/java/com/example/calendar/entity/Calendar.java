package com.example.calendar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Calendar {

    private Long id;
    private String user;
    private String contents;
    private String password;
    private String date;

    public Calendar(String user, String contents, String password, String date) {
        this.user = user;
        this.contents = contents;
        this.password = password;
        this.date = date;
    }

    public void update(String user, String contents) {
        this.user = user;
        this.contents = contents;
    }
}
