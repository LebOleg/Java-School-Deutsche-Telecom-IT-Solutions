package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TrainDTO {

    public TrainDTO(int id, int availableSeats, String routeNumber) {
        this.id = id;
        this.availableSeats = availableSeats;
        this.routeNumber = routeNumber;
    }

    private int id;
    private int availableSeats;
    private String routeNumber;
}
