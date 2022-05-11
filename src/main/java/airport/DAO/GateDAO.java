package airport.DAO;

import airport.entity.Gate;
import airport.entity.Terminal;

import java.util.List;

public interface GateDAO extends GenericDAO<Gate> {
    List<Gate> getGatesByTerminal(Terminal terminal);
}
