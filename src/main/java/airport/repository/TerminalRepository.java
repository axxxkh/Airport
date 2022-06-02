package airport.repository;

import airport.entity.Terminal;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends GenericJPARepository<Terminal> {
}
