package com.mitocode.mitoacademia.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    
	@ExceptionHandler(Exception.class)
    public ResponseEntity<CustomeErrorResponse> handleAllException(Exception ex, WebRequest req){
		log.info("ERROR: Generico: {}",ex);
        CustomeErrorResponse errorResponse = new CustomeErrorResponse(LocalDateTime.now(), ex.getLocalizedMessage(), req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomeErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        CustomeErrorResponse errorResponse = new CustomeErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {

        String message = ex.getBindingResult().getFieldErrors()
                            .stream()
                            .map(error->error.getField()+":"+error.getDefaultMessage())
                            .collect(Collectors.joining(","));
        CustomeErrorResponse errorResponse = new CustomeErrorResponse(LocalDateTime.now(), message, req.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        CustomeErrorResponse errorResponse = new CustomeErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
   @Override
   protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
	   CustomeErrorResponse errorResponse = new CustomeErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));

	return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
}
}
