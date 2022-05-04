package Airport.DAO;

import Airport.Entity.Gate;
import Airport.Entity.Terminal;

import java.util.List;

public interface GateDAO extends GenericDAO<Gate>{
    List<Gate> getGatesByTerminal (Terminal terminal);
}
