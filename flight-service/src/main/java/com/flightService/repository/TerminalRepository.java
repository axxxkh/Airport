package com.flightService.repository;

import com.flightService.entity.Terminal;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends GenericJPARepository<Terminal> {
}
