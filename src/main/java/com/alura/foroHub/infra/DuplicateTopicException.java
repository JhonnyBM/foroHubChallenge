package com.alura.foroHub.infra;

public class DuplicateTopicException extends RuntimeException {
    public DuplicateTopicException(String message) {
        super(message);
    }
}
