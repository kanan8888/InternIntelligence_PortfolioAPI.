package az.portfolioapi.dto.User;

import az.portfolioapi.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    //static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{4,20}$";

    @NotBlank(message = "{validation.user.first.name.not.blank}")
    @Size(max = 100, message = "{validation.user.firstname.size}")
    String firstName;

    @NotBlank(message = "{validation.user.last.name.not.blank}")
    @Size(max = 100, message = "{validation.user.last.name.size}")
    String lastName;

    @NotBlank(message = "{validation.user.username.not.blank}")
    @Size(min = 5, max = 50, message = "{validation.user.username.size}")
    String username;

    @NotBlank(message = "{validation.user.email.not.blank}")
    @Email(message = "{validation.user.email.email}")
    @Size(max = 254, message = "{validation.user.email.size}")
    String email;

//    @NotBlank(message = "{validation.user.password.not.blank}")
//    @Size(min = 4, max = 20, message = "{validation.user.password.size}")
//    @Pattern(regexp = PASSWORD_REGEX, message = "{validation.user.password.pattern}")
    @JsonIgnore
    String password;

    @JsonIgnore //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "{validation.user.role.notnull}")
    UserRole role = UserRole.ADMIN;
}
