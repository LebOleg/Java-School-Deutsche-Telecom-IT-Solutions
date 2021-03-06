package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "train")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Train {

    public Train(Integer availableSeats, RouteNumber routeNumber) {
        this.availableSeats = availableSeats;
        this.routeNumber = routeNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "seats_available")
    private Integer availableSeats;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "train")
    private Timetable timetable;
    @ManyToOne
    @JoinColumn(name = "route_number", referencedColumnName = "number")
    private RouteNumber routeNumber;

}
