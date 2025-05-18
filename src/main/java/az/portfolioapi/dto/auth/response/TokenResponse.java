package az.portfolioapi.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TokenResponse {

    String accessToken;

    @JsonIgnore // refreshtoken cookie yə yazılacaqsa, jsonignore etmək məntiqlidir sanki
    String refreshToken;
}
