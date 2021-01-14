package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "timetable")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "train_number")
    private Train train;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "station_name")
    private Station station;
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;


}
