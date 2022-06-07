package flightMicroService.repository;

import flightMicroService.entity.Terminal;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends GenericJPARepository<Terminal> {
}
