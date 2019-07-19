package com.converter.dependencyConverter.exception;

import com.converter.dependencyConverter.exception.exceptions.JSONConversionException;
import com.converter.dependencyConverter.exception.exceptions.MavenConversionException;
import com.converter.dependencyConverter.exception.exceptions.YAMLConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({JSONConversionException.class, MavenConversionException.class, YAMLConversionException.class, Throwable.class})
    public final ResponseEntity<CustomError> handleException(Exception ex, WebRequest webRequest){

        LOGGER.error("Handling " + ex.getClass().getSimpleName() + " due to " + ex.getMessage());

        HttpHeaders headers = new HttpHeaders();

        if(ex instanceof JSONConversionException){
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

            JSONConversionException jsonConversionException = (JSONConversionException) ex;

            return handleJsonConversionException(jsonConversionException, headers, httpStatus, webRequest);
        }

        if(ex instanceof MavenConversionException){
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

            MavenConversionException mavenConversionException = (MavenConversionException) ex;

            return handleMavenConversionException(mavenConversionException, headers, httpStatus, webRequest);
        }

        if(ex instanceof YAMLConversionException){
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

            YAMLConversionException yamlConversionException = (YAMLConversionException) ex;

            return handleYamlConversionException(yamlConversionException, headers, httpStatus, webRequest);
        }

        if(ex instanceof Throwable){
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

            Throwable throwable = ex;

            return handleThrowable(throwable, headers, httpStatus, webRequest);

        }

        else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Unknown exception type: " + ex.getClass().getName());
            }

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, webRequest);
        }
    }

    protected ResponseEntity<CustomError> handleExceptionInternal(Throwable ex, @Nullable CustomError customError
            , HttpHeaders headers
            , HttpStatus status, WebRequest webRequest) {

        if(HttpStatus.INTERNAL_SERVER_ERROR.equals(status)){
            webRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(customError, headers, status);
    }

    protected ResponseEntity<CustomError> handleJsonConversionException(JSONConversionException jsonConversionException
            , HttpHeaders headers, HttpStatus httpStatus, WebRequest webRequest) {

        List<String> errors = Collections.singletonList(jsonConversionException.getMessage());

        return handleExceptionInternal(jsonConversionException, new CustomError(errors), headers, httpStatus, webRequest);

    }

    protected ResponseEntity<CustomError> handleMavenConversionException(MavenConversionException mavenConversionException
            , HttpHeaders headers, HttpStatus httpStatus, WebRequest webRequest) {

        List<String> errors = Collections.singletonList(mavenConversionException.getMessage());

        return handleExceptionInternal(mavenConversionException, new CustomError(errors), headers, httpStatus, webRequest);
    }

    protected ResponseEntity<CustomError> handleYamlConversionException(YAMLConversionException yamlConversionException
            , HttpHeaders headers, HttpStatus httpStatus, WebRequest webRequest) {

        List<String> errors = Collections.singletonList(yamlConversionException.getMessage());

        return handleExceptionInternal(yamlConversionException, new CustomError(errors), headers, httpStatus, webRequest);
    }

    protected ResponseEntity<CustomError> handleThrowable(Throwable throwable, HttpHeaders headers, HttpStatus httpStatus
            , WebRequest webRequest) {

        List<String> errors = Collections.singletonList(throwable.getMessage());

        return handleExceptionInternal(throwable, new CustomError(errors), headers, httpStatus, webRequest);
    }
}
