package com.flightService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PassengerDTO {
    private long id;
    @NotBlank(message = "First name can`t be blank")
    private String name;
    @NotBlank(message = "Surname can`t be blank")
    private String surname;
    private PassportDTO passport;
    private List<TicketDTO> tickets;
}
