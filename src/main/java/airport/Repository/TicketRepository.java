package airport.Repository;

import airport.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("from Ticket t LEFT JOIN FETCH t.flight f where f.id= ?1")
    List<Ticket> findTicketsByFlightId(Integer flightId);

    @Query("from Ticket t LEFT JOIN FETCH t.passenger p where p.id= ?1")
    List<Ticket> findTicketsByPassengerId(Integer passengerId);

    List<Ticket> findByActiveTrue();


}
