package az.portfolioapi.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank(message = "{validation.login.username.not.blank}")
    @Size(max = 50, message = "{validation.login.username.size}")
    String username;

    @NotBlank(message = "{validation.login.password.not.blank}")
    @Size(max = 20, message = "{validation.login.password.size}")
    String password;
}


/*
    private String usernameOrEmail;
*/
