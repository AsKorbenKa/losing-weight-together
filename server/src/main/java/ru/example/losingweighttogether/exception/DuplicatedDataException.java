package ru.example.losingweighttogether.exception;

public class DuplicatedDataException extends RuntimeException {
    public DuplicatedDataException(final String message) {
        super(message);
    }
}
