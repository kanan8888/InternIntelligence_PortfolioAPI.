package az.portfolioapi.dto.User;

import az.portfolioapi.entity.enums.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long id;
    String username;
    String firstName;
    String lastName;
    String email;
    UserRole role;
    List<Long> portfolioIds;
}
