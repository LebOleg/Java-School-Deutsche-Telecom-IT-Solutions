package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PassengerDTO {
    private String name;
    private String lastName;
    private String middleName;
    private String birthday;
    private String email;
    private String passportNumber;
    private TicketDTO ticketDTO;
}
