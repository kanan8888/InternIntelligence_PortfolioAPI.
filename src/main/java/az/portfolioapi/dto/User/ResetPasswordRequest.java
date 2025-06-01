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

    @NotBlank(message = "{validation.resetpassword.currentpassword.notblank}")
    String currentPassword;

    @NotBlank(message = "{validation.resetpassword.newpassword.notblank}")
    @Size(min = 4, max = 20, message = "{validation.resetpassword.newpassword.size}")
    @Pattern(regexp = PASSWORD_REGEX, message = "{validation.resetpassword.newpassword.pattern}")
    String newPassword;

    @NotBlank(message = "{validation.resetpassword.confirmpassword.notblank}")
    String confirmPassword;
}
