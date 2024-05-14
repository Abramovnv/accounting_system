package org.example.accounting_system.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accounting_system.entity.ErrorResponse;
import org.example.accounting_system.repository.ErrorResponseRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final ErrorResponseRepository errorResponseRepository;

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerDataIntegrityViolationException
            (DataIntegrityViolationException dataIntegrityViolationException, HttpServletRequest httpServletRequest) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .url(httpServletRequest.getRequestURI())
                .status(HttpStatus.CONFLICT.value())
                .error(dataIntegrityViolationException.getClass().getSimpleName())
                .message(dataIntegrityViolationException.getMessage())
                .build();
        log.error(errorResponse.toString());
        errorResponseRepository.save(errorResponse);
        return errorResponse;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerEntityNotFoundException
            (EntityNotFoundException entityNotFoundException, HttpServletRequest httpServletRequest) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .url(httpServletRequest.getRequestURI())
                .status(HttpStatus.CONFLICT.value())
                .error(entityNotFoundException.getClass().getSimpleName())
                .message(entityNotFoundException.getMessage())
                .build();
        log.error(errorResponse.toString());
        errorResponseRepository.save(errorResponse);
        return errorResponse;
    }
}
