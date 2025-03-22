package ru.example.losingweighttogether.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<ApiError> handleDuplicatedData(NotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.builder()
                        .status("NOT_FOUND")
                        .reason("Искомый объект не найден.")
                        .message(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleDuplicatedData(DuplicatedDataException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiError.builder()
                        .status("CONFLICT")
                        .reason("Запрос не может быть выполнен из-за конфликта с текущим состоянием целевого ресурса.")
                        .message(e.getMessage())
                        .build()
                );
    }
}
