package airport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Ticket {
    @Id
    private int id;
    private int number;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;
    private int seat;
    @Column(name = "ticket_status")
    private int ticketStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passanger_id")
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
}
