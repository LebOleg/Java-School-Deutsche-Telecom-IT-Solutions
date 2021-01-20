package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SearchStationTimetableDTO {

    private String stationName;
    private String date;
}
