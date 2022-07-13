package flightMicroService.controller;

import flightMicroService.dto.RouteDTO;
import flightMicroService.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {

    private RouteService routeService;

    @DeleteMapping ("/")
    public String deleteRoute(@RequestBody @Valid RouteDTO routeDTO) {
        routeService.delete(routeDTO);
        return "";
    }
}

