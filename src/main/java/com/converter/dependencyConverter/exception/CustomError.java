package com.converter.dependencyConverter.exception;

import java.util.List;

public class CustomError {
    private List<String> errors;

    public CustomError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
