package airport.Repository;

import airport.entity.Gate;
import airport.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GateRepository extends GenericJPARepository<Gate> {
    List<Gate> getGatesByTerminal(Terminal terminal);
}
