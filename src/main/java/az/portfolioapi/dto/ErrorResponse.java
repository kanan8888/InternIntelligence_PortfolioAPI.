package az.portfolioapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String message;
    private Instant timestamp;
    private String path;
}
