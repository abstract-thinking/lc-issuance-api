package com.example.api.domain;

public class AlreadySubmittedException extends DomainException {
    public AlreadySubmittedException(String message) {
        super(message);
    }
}
