package com.flightService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {
    private Long id;
    @NotBlank(message = "Flight number is mandatory")
    private Integer flightNumber;
    @NotBlank(message = "Ticket number can`t be blank")
    private Integer number;
    @NotBlank(message = "Ticket seat can`t be blank")
    private Integer seat;
    private Long passengerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDTO ticketDTO = (TicketDTO) o;
        return getFlightNumber().equals(ticketDTO.getFlightNumber()) && getSeat().equals(ticketDTO.getSeat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightNumber(), getSeat());
    }
}
