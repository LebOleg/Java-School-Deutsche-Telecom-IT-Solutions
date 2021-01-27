package ru.lebedev.SBBProject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lebedev.SBBProject.model.Passenger;
import ru.lebedev.SBBProject.utility.CustomConverter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PassengerDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String middleName;
    @NotEmpty
    private String birthday;
    private String email;
    @NotEmpty
    private String passportNumber;
    private TicketDTO ticketDTO;

    public static Passenger convertToPassenger(PassengerDTO passengerDTO) {
        String name = passengerDTO.getName();
        String lastName = passengerDTO.getLastName();
        String middleName = passengerDTO.getMiddleName();
        String email = passengerDTO.getEmail();
        LocalDate birth = CustomConverter.convertStringToDate(passengerDTO.getBirthday());
        String passport = passengerDTO.getPassportNumber();

        Passenger passenger = Passenger.createPassenger(name, lastName, middleName, birth, email, passport);

        return passenger;
    }
}
