package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "railway")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Railway {

    public Railway(Station fromStation, Station toStation) {
        this.fromStation = fromStation;
        this.toStation = toStation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "from_station")
    private Station fromStation;
    @ManyToOne
    @JoinColumn(name = "to_station")
    private Station toStation;
}
