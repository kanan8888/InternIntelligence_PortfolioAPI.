package az.portfolioapi.dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPasswordRequest {

    static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{4,20}$";

    @NotBlank(message = "{validation.reset.password.current.password.not.blank}")
    String currentPassword;

    @NotBlank(message = "{validation.reset.password.new.password.not.blank}")
    @Size(min = 4, max = 20, message = "{validation.reset.password.new.password.size}")
    @Pattern(regexp = PASSWORD_REGEX, message = "{validation.reset.password.new.password.pattern}")
    String newPassword;

    @NotBlank(message = "{validation.reset.password.confirm.password.not.blank}")
    String confirmPassword;
}
