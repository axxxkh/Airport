package com.flightService.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"flight_Id", "seat"})})
public class Ticket extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
    @NonNull
    private Integer seat;
    @Column(name = "ticket_status", nullable = false)
    private byte ticketStatus;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @Column(name = "buy_date")
    private LocalDate buyDate;
    private boolean active = Boolean.TRUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getSeat().equals(ticket.getSeat())
                && getFlight().equals(ticket.getFlight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getFlight(), getSeat());
    }
}
