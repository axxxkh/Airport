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
public class TicketDTO {
    @NotBlank(message = "Flight number is mandatory")
    private int flightNumber;
    @NotBlank(message = "Ticket number can`t be blank")
    private int number;
    @NotBlank(message = "Ticket seat can`t be blank")
    private int seat;
//    private PassengerDTO passengerDTO;
}
