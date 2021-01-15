package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TicketDTO {
    private String sourceStation;
    private String destinationStation;
    private String departureTime;
    private String arrivalTime;
    private String dateTicket;
    private String train;
    private int availableSeats;
}
