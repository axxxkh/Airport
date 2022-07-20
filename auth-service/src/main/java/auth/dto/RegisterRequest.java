package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String secretQuestion;
    @NotNull String secretAnswer;

}
