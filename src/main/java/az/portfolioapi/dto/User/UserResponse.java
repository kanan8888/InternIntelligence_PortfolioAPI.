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
    String fullName;
    String username;
    String email;
    UserRole role;
    List<Long> portfolioIds;
}
