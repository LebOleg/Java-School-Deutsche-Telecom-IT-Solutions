package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "station")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Station {

    public Station(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "name", columnDefinition = "VARCHAR(64)")
    private String name;
}
