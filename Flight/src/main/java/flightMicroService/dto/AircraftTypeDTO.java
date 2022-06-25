package flightMicroService.dto;

import javax.validation.constraints.NotBlank;

public class AircraftTypeDTO {
    @NotBlank(message = "Aircraft producer is mandatory")
    private String producer;
    @NotBlank(message = "Aircraft type producer is mandatory")
    private String type;
}
