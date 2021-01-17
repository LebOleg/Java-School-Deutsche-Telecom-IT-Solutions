package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Passenger {
    @Id
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "date_of_birth")
    private LocalDate birthday;
    @Column(name = "email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public static Passenger createPassenger(String name, String lastName, String middleName,
                                            LocalDate birthday, String email, String passportNumber) {
        Passenger passenger = new Passenger();
        passenger.name = name;
        passenger.lastName = lastName;
        passenger.middleName = middleName;
        passenger.birthday = birthday;
        passenger.email = email;
        passenger.passportNumber = passportNumber;

    return passenger;
    }
}
