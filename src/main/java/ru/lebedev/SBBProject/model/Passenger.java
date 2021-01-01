package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "passenger")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Passenger {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "date_of_birth")
    private LocalDateTime birthday;
    @Column(name = "email")
    private String email;
    @Column(name = "passport_number")
    private String passportNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;
}
