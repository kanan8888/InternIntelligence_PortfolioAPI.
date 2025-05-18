package az.portfolioapi.dto.auth.request;

import az.portfolioapi.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

    static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";

    @NotBlank(message = "Full name cannot be blank")
    @Size(min = 5, max = 50, message = "Full name must be 5-50 characters")
    String fullName;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 5, max = 20, message = "Username must be 5-20 characters")
    String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4, max = 20, message = "Password must be 4-20 characters")
    @Pattern(regexp = PASSWORD_REGEX, message = "Password must contain at least 1 uppercase letter (A-Z), 1 lowercase letter (a-z), and 1 digit (0-9)")
    String password;

    @JsonIgnore  //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    UserRole role = UserRole.MEMBER;
}

/*
    String firstname
    String lastname
 */


/*
    private static final String EMAIL_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
 */
