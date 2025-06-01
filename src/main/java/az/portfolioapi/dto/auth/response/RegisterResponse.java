package az.portfolioapi.dto.auth.response;

import az.portfolioapi.entity.enums.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RegisterResponse {

    Long id;
    String username;
    String firstName;
    String lastName;
    String email;
    UserRole role;
}
