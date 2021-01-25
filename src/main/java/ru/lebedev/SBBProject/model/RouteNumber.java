package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "route_number")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RouteNumber {

    public RouteNumber(String number) {
        this.number = number;
    }

    @Id
    @Column(name = "number", columnDefinition = "VARCHAR(64)")
    private String number;

    @OneToMany(mappedBy = "routeNumber")
    private List<Route> routes;

    @OneToMany(mappedBy = "routeNumber")
    private List<Train> trains;
}
