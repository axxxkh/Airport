package airport.controller;

import airport.dto.RouteDTO;
import airport.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {

    private RouteService routeService;

    @GetMapping("/delete/")
    public String deleteRoute(RouteDTO routeDTO) {

        routeService.delete(routeDTO);
        return "";
    }
}

