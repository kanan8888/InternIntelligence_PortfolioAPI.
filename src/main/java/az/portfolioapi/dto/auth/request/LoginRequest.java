package az.portfolioapi.dto.auth.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    //@NotBlank
    String userName;

    //@NotBlank
    String password;
}


/*

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

 */
