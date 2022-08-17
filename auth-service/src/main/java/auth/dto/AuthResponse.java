package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@Builder
@Validated
public class AuthResponse {
    private String email;
    private String accessToken;
}
