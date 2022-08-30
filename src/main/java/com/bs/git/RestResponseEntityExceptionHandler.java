package com.bs.git;

import com.bs.git.httpclient.exceptions.NotFoundException;
import com.bs.git.repos.dto.GitApiErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        GitApiErrorDto apiError = GitApiErrorDto.builder()
                .status("404")
                .message("Username not found")
                .build();
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers,
                                                                      HttpStatus status, WebRequest request) {
        GitApiErrorDto apiError = GitApiErrorDto.builder()
                .status("406")
                .message("Header Accept: application/xml not acceptable")
                .build();
        return handleExceptionInternal(ex, apiError,
                headers, HttpStatus.NOT_ACCEPTABLE, request);
    }
}
