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

    public Timetable(Train train, RouteNumber route, LocalTime beginTime, LocalDate date) {
        this.train = train;
        this.route = route;
        this.beginTime = beginTime;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "train_number", referencedColumnName = "id")
    private Train train;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "route_number", referencedColumnName = "number")
    private RouteNumber route;
    @Column(name = "begin_time")
    private LocalTime beginTime;
    @Column(name = "date")
    private LocalDate date;
}
