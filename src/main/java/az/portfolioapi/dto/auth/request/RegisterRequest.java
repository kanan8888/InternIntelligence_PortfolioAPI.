package az.portfolioapi.dto.auth.request;

import az.portfolioapi.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

    //@NotBlank(message = "Full name cannot be blank")
    String fullName;

    //@NotBlank(message = "Username cannot be blank")
    String userName;

    //@Email(message = "Email should be valid")
    //@NotBlank(message = "Email cannot be blank")
    String email;

    //@NotBlank(message = "Password cannot be blank")
    String password;

    @JsonIgnore  //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    UserRole role = UserRole.MEMBER; //rolu default olaraq member etmek ucun....
}
