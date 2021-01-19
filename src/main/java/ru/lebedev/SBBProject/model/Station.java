package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "name")
    private String name;
}
