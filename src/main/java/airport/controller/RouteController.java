package airport.controller;

import airport.DTO.RouteDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {


    @GetMapping("/delete/")
    public String deleteRoute(RouteDTO routeDTO) {

        return "";
    }
}

