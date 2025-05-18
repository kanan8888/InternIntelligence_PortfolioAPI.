package az.portfolioapi.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 5, max = 50, message = "Username must be 5-20 characters")
    String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4, max = 20, message = "Password must be 4-20 characters")
    String password;
}


/*
    private String usernameOrEmail;
*/
