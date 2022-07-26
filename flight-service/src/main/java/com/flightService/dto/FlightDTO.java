package com.flightService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDTO {
    @NotBlank(message = "Flight number is mandatory")
    private int flightNumber;
    @NotBlank(message = "Flight time and date is mandatory")
    private LocalDateTime time;
    private byte flightStatus;
    @NotBlank(message = "Flight airline is mandatory")
    private AirlineDTO airline;
    @NotBlank(message = "Aircraft type is mandatory")
    private AircraftDTO craftId;
    private GateDTO gate;
//    private String route;
}
