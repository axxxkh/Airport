package com.flightService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PassengerDTO {
    @NotBlank(message = "First name can`t be blank")
    private String name;
    @NotBlank(message = "Surname can`t be blank")
    private String surname;
    private List<PassportDTO> passports;
    @Email
    private String username;
    private String password;
    private Set<RoleDTO> roles;
}
