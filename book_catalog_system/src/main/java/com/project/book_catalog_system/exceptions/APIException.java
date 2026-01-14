package com.project.book_catalog_system.exceptions;

public class APIException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }
}
