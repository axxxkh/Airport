package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class RegisterRequest {
    @NotNull
    @Email(message = "Wrong format email")
    private String email;
    @NotBlank
    private String password;
    @NotBlank(message = "Secret question can't be blank")
    private String secretQuestion;
    @NotBlank(message = "Secret answer can't be blank")
    private String secretAnswer;

}
