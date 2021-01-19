package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "route")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Route {

    public Route(RouteNumber routeNumber, Station station, String travelTime) {
        this.routeNumber = routeNumber;
        this.station = station;
        this.travelTime = travelTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "route_number",  referencedColumnName = "number")
    private RouteNumber routeNumber;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "station_name")
    private Station station;
    @Column(name = "travel_time")
    private String travelTime;
}
