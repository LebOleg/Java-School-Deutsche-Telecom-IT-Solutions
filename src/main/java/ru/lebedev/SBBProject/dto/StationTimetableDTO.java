package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class StationTimetableDTO{
    private String routeNumber;
    private LocalDateTime departureTime;
}
