package com.flightService.repository;

import com.flightService.entity.Ticket;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends GenericJPARepository<Ticket> {

    @Query("from Ticket t LEFT JOIN FETCH t.flight f where f.id= ?1")
    List<Ticket> findTicketsByFlightId(long flightId);

    @Query("from Ticket t LEFT JOIN FETCH t.flight f where f.flightNumber= ?1")
    List<Ticket> findTicketsByFlightNumber(long flightNumber);

    @Query("from Ticket t LEFT JOIN FETCH t.passenger p where p.id= ?1")
    List<Ticket> findTicketsByPassengerId(long passengerId);

    List<Ticket> findByActiveTrue();


}
