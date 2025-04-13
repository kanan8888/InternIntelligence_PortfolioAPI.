package az.portfolioapi.dto.request;

import az.portfolioapi.entity.enums.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

//    @NotEmpty(message = "Username cannot be empty")
//    @Size(min = 5, max = 30, message = "Username size must be between 5 and 30")
    String userName;

//    @NotEmpty(message = "Email cannot be empty")
//    @Email
    String email;

//    @NotEmpty(message = "Password cannot be empty")
//    @Size(min = 5, max = 25,message = "Password size must be between 5 and 25")
    String password;

    UserRole role;
}
