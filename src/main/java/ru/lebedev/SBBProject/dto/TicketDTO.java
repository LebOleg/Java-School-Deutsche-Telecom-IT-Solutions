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
public class TicketDTO {

    public TicketDTO(String sourceStation, String destinationStation, String train,
                     String routeNumber, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int availableSeats) {
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.train = train;
        this.routeNumber = routeNumber;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.availableSeats = availableSeats;
    }

    private String sourceStation;
    private String destinationStation;
    private String departureTime;
    private String arrivalTime;
    private String dateTicket;
    private String train;
    private String routeNumber;
    private int availableSeats;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
}
