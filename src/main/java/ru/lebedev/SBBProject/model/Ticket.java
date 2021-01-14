package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Ticket {

    public Ticket(Station sourceStation, Station destinationStation,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, Train train) {
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.train = train;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "source_station", referencedColumnName = "name")
    private Station sourceStation;
    @OneToOne
    @JoinColumn(name = "destination_station", referencedColumnName = "name")
    private Station destinationStation;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    @ManyToOne
    @JoinColumn(name = "train_number", referencedColumnName = "number")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Passenger passenger;
}