package auth.auth;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
