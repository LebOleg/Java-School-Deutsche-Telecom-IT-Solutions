package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
