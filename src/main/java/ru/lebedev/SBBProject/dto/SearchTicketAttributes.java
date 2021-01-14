package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SearchTicketAttributes {
    private String fromStation;
    private String toStation;
    private String fromTime;
    private String toTime;
    private String date;

    public LocalDateTime convertStringsToDate(String time, String date) {
        String fullDate = date + "T" + time;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        return LocalDateTime.parse(fullDate, formatter);
    }
}
