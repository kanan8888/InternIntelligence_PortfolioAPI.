package az.portfolioapi.security;

import az.portfolioapi.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final MessageSource messageSource;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }

        log.warn("Access denied for IP: {}, Method: {}, URI: {}, message: {}",
                ipAddress,
                request.getMethod(),
                request.getRequestURI(),
                accessDeniedException.getMessage());

        Locale locale = request.getLocale();

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN,
                messageSource.getMessage("error.access.denied", null, locale),
                Instant.now().toString(),
                request.getRequestURI()
        );

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //response.setHeader("Cache-Control", "no-store");
        response.getWriter().write(OBJECT_MAPPER.writeValueAsString(errorResponse));
    }
}
