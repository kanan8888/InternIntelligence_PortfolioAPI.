package az.portfolioapi.dto.auth.response;

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
    String fullName;
    String username;
    String email;
}

//    private LocalDateTime registrationDate;

/*
    String firstname
    String lastname
 */