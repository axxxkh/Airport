package com.flightService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftDTO {
    @NotBlank(message = "Aircraft type is mandatory")
    private String typeId;
    @NotBlank(message = "Aircraft producer is mandatory")
    private String producer;
    @NotBlank(message = "Aircraft serial number is mandatory")
    private int serialNumber;
}
