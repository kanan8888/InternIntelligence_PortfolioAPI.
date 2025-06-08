package az.portfolioapi.dto.auth.request;

import az.portfolioapi.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

    static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{4,20}$";

    @NotBlank(message = "{validation.register.first.name.not.blank}")
    @Size(max = 100, message = "{validation.register.first.name.size}")
    String firstName;

    @NotBlank(message = "{validation.register.last.name.not.blank}")
    @Size(max = 100, message = "{validation.register.last.name.size}")
    String lastName;

    @NotBlank(message = "{validation.register.username.not.blank}")
    @Size(min = 5, max = 50, message = "{validation.register.username.size}")
    String username;

    @NotBlank(message = "{validation.register.email.not.blank}")
    @Email(message = "{validation.register.email.email}")
    @Size(max = 254, message = "{validation.register.email.size}")
    String email;

    @NotBlank(message = "{validation.register.password.not.blank}")
    @Size(min = 4, max = 20, message = "{validation.register.password.size}")
    @Pattern(regexp = PASSWORD_REGEX, message = "{validation.register.password.pattern}")
    String password;

    @JsonIgnore  //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "{validation.register.role.not.null}")
    UserRole role = UserRole.MEMBER;
}


/*
    private static final String EMAIL_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
*/
