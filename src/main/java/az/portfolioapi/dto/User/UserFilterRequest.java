package az.portfolioapi.dto.User;

import az.portfolioapi.entity.enums.DegreeLevel;
import az.portfolioapi.entity.enums.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFilterRequest {
    String username;
    String firstName;
    String lastName;
    String email;
    UserRole role;
    String educationInstitution;
    DegreeLevel educationDegree;
    String experienceCompany;
    String experiencePosition;
    String skillName;
}
