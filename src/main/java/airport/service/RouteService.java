package airport.service;

import airport.dto.RouteDTO;
import org.springframework.stereotype.Service;

public interface RouteService {

    void delete(RouteDTO routeDTO);
}
