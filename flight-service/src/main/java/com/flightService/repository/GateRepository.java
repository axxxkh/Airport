package com.flightService.repository;

import com.flightService.entity.Gate;
import com.flightService.entity.Terminal;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GateRepository extends GenericJPARepository<Gate> {
    List<Gate> getGatesByTerminal(Terminal terminal);
}
