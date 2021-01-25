package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SearchStationTimetableDTO {

    @NotBlank(message = "Требуется ввести название станции")
    private String stationName;
    @NotBlank(message = "Требуется ввести дату")
    private String date;
}
