package airport.Repository;

import airport.entity.Gate;
import airport.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GateRepository extends JpaRepository<Gate, Integer> {
    List<Gate> getGatesByTerminal(Terminal terminal);
}
