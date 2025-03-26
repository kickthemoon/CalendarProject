package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class JdbcTemplateCalendarRepository implements CalendarRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCalendarRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public CalendarResponseDto saveCalendar(Calendar calendar) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(formatter);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("user", calendar.getUser());
        parameters.put("contents", calendar.getContents());
        parameters.put("password", calendar.getPassword());
        parameters.put("date", formattedDate);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalendarResponseDto(key.longValue(), calendar.getUser(),calendar.getContents(),calendar.getPassword(),formattedDate);
    }

    @Override
    public List<CalendarResponseDto> findAllCalendar() {
        return jdbcTemplate.query("select * from calendar",calendarAllRowMapper());
    }

    @Override
    public Optional<Calendar> findCalendarById(Long id) {
        List<Calendar> result = jdbcTemplate.query("select * from calendar where id = ?", calendarSelectRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Calendar findCalendarByIdOrElseThrow(Long id) {
        List<Calendar> result = jdbcTemplate.query("select * from calendar where id = ?", calendarSelectRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾는 ID는 없습니다: " + id));
    }

    @Override
    public int updateCalendar(Long id, String user, String contents) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(formatter);

        return jdbcTemplate.update("update calendar set user = ?, contents = ?,date = ? where id = ? ", user, contents,formattedDate, id);
    }

    @Override
    public int deleteCalendar(Long id) {
        return jdbcTemplate.update("delete from calendar where id = ?",id);
    }

    private RowMapper<CalendarResponseDto> calendarAllRowMapper() {
        return new RowMapper<CalendarResponseDto>() {
            @Override
            public CalendarResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalendarResponseDto(
                    rs.getLong("id"),
                    rs.getString("user"),
                    rs.getString("contents"),
                    rs.getString("password"),
                    rs.getString("date")
                );
            }
        };
    }

    private RowMapper<Calendar> calendarSelectRowMapper() {
        return new RowMapper<Calendar>() {
            @Override
            public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calendar(
                        rs.getLong("id"),
                        rs.getString("user"),
                        rs.getString("contents"),
                        rs.getString("password"),
                        rs.getString("date")
                );
            }
        };
    }
}
