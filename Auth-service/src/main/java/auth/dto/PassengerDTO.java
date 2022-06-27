package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PassengerDTO {
    @NotBlank(message = "First name can`t be blank")
    private String name;
    @NotBlank(message = "Surname can`t be blank")
    private String surname;
    @Email
    private String username;
    private String password;
}
