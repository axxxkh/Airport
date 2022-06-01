package airport.entity;

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
public class Ticket extends BasicEntity{
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
        return getNumber() == ticket.getNumber()
                && getSeat() == ticket.getSeat()
                && getFlight().equals(ticket.getFlight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getFlight(), getSeat());
    }
}
