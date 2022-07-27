package com.flightService.repository;

import com.flightService.entity.Ticket;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends GenericJPARepository<Ticket> {

    @Query("from Ticket t LEFT JOIN FETCH t.flight f where f.id= ?1")
    List<Ticket> findTicketsByFlightId(Long flightId);

    @Query("from Ticket t LEFT JOIN FETCH t.passenger p where p.id= ?1")
    List<Ticket> findTicketsByPassengerId(Long passengerId);

    List<Ticket> findByActiveTrue();


}
