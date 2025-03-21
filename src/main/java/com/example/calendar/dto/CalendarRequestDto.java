package com.example.calendar.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@Getter
public class CalendarRequestDto {
    private Long month;
    private Long day;
    private String title;
    private String contents;

    public CalendarRequestDto() {
        exceptionMonth();
        exceptionDay();
    }

    private ResponseEntity exceptionMonth() {
        if (month<=0 || month >12){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(month, HttpStatus.OK);
    }

    private ResponseEntity exceptionDay() {
        if (day <=0 || day > 31) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }   else {
            int intTemp = Math.toIntExact(month);

//            Set<Integer> limitDay31 = Set.of(1,3,5,7,8,10,12);
            Set<Integer> limitDay30 = Set.of(4,6,8,11);
            Set<Integer> limitDay28 = Set.of(2);

            if (limitDay28.contains(intTemp)) {
                if(day > 28) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            } else if (limitDay30.contains(intTemp)) {
                if(day > 30) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
            }

        }
        return new ResponseEntity<>(day, HttpStatus.OK);
    }
}
