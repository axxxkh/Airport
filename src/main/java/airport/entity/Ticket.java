package airport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"flight_Id", "seat"})})
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int number;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    private int seat;
    @Column(name = "ticket_status")
    private byte ticketStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    private boolean active;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", number=" + number +
                ", seat=" + seat +
                ", ticketStatus=" + ticketStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getNumber() == ticket.getNumber()
                && getSeat() == ticket.getSeat()
                && getFlight().equals(ticket.getFlight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getFlight(), getSeat());
    }
}
