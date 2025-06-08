package az.portfolioapi.exception;

import az.portfolioapi.exception.common.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j /**/
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBaseException(BaseException ex,
                                                 WebRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getHttpStatus(),
                messageSource.getMessage(ex.getMessageKey(), null, locale),
                Instant.now().toString(),
                request.getDescription(false));
//        log.error("Handled BaseException: {}", ex.getLogMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
//        log.warn("Validation failed: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        Locale locale = LocaleContextHolder.getLocale();

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                messageSource.getMessage("error.invalid.request", null, locale),
                Instant.now().toString(),
                request.getDescription(false)
        );

        log.warn("Invalid request payload", ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex,
                                                                     WebRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN,
                messageSource.getMessage("error.access.denied", null, locale),
                Instant.now().toString(),
                request.getDescription(false)
        );
//        log.warn("Access denied: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex,
                                                               WebRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                messageSource.getMessage("error.internal.server", null, locale),
                Instant.now().toString(),
                request.getDescription(false)
        );
        log.error("Unexpected exception: ", ex); /**/
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

/*
        1) format xətalarını nəzərə almaq gərəklidir:
           enum tarix kimi sahələrdə xəta olduqda aydın şəkildə bildirilməlidir
        2) rol ilə əlaqədar xətaları oxunaqlı etmək gərəklidir
 */