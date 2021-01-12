package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "train")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Train {

    @Id
    @Column(name = "number")
    private String number;
    @Column(name = "seats_available")
    private Integer availableSeats;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train")
    private List<Timetable> timetable;
}
