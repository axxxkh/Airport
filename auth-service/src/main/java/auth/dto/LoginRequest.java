package auth.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest implements Serializable {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
