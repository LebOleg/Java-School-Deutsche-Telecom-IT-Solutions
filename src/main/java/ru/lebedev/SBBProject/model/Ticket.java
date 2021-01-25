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

    public Ticket(LocalDateTime departureTime, LocalDateTime arrivalTime, Train train) {
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
    @JoinColumn(name = "train_number", referencedColumnName = "id")
    private Train train;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "passenger", referencedColumnName = "id")
    private Passenger passenger;

    public static Ticket createTicket(Station sourceStation, Station destinationStation, LocalDateTime departureTime,
                                      LocalDateTime arrivalTime, Train train) {

        Ticket ticket = new Ticket();

        ticket.sourceStation = sourceStation;
        ticket.destinationStation = destinationStation;
        ticket.departureTime = departureTime;
        ticket.arrivalTime = arrivalTime;
        ticket.train = train;

        return ticket;
    }
}