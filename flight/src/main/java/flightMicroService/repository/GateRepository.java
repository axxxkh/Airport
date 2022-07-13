package flightMicroService.repository;

import flightMicroService.entity.Gate;
import flightMicroService.entity.Terminal;

import java.util.List;

public interface GateRepository extends GenericJPARepository<Gate> {
    List<Gate> getGatesByTerminal(Terminal terminal);
}
