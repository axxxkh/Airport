package flightMicroService.controller;

import flightMicroService.dto.RouteDTO;
import flightMicroService.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {

    private RouteService routeService;

    @DeleteMapping("/")
    public String deleteRoute(@RequestBody @Valid RouteDTO routeDTO) {
        routeService.delete(routeDTO);
        return "";
    }
}

