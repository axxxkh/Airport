package com.flightService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalDTO {
    @NotBlank(message = "Terminal name can`t be blank")
    private String name;
}
