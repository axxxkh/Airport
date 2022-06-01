package airport.Repository;

import airport.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends GenericJPARepository<Terminal> {
}
