package com.flightService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GateDTO {
    private Long id;
    private Long capacity;
    @NotBlank(message = "Gate terminal is mandatory")
    private TerminalDTO terminal;
}
