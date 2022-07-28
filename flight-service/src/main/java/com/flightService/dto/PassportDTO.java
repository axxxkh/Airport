package com.flightService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassportDTO {
    @NotBlank(message = "Passport serial number is mandatory")
    private String serialNumber;
    @NotBlank(message = "Passenger birthday is mandatory")
    private LocalDate birthdate;
    private LocalDate issueDate;
}
