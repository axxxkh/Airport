package com.flightService.repository;

import com.flightService.entity.Gate;
import com.flightService.entity.Terminal;

import java.util.List;

public interface GateRepository extends GenericJPARepository<Gate> {
    List<Gate> getGatesByTerminal(Terminal terminal);
}
