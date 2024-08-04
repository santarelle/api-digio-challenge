package com.digio.challenge.infrastructure.exceptionhandler;

import com.digio.challenge.application.exception.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<Object> handleBusinessLogicException(BusinessLogicException ex, WebRequest webRequest) {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .title(httpStatusCode.toString())
                .status(httpStatusCode.value())
                .detail(ex.getMessage())
                .build();
        log.error("Handler error business logic exception {}", errorResponse, ex);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatusCode, webRequest);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest webRequest) {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .title(httpStatusCode.toString())
                .status(httpStatusCode.value())
                .detail(ex.getMessage())
                .build();
        log.error("Handler error product not found exception {}", errorResponse);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatusCode, webRequest);
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<Object> handlePurchaseNotFoundException(PurchaseNotFoundException ex, WebRequest webRequest) {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .title(httpStatusCode.toString())
                .status(httpStatusCode.value())
                .detail(ex.getMessage())
                .build();
        log.error("Handler error purchase not found exception {}", errorResponse);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatusCode, webRequest);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest webRequest) {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .title(httpStatusCode.toString())
                .status(httpStatusCode.value())
                .detail(ex.getMessage())
                .build();
        log.error("Handler error customer not found exception {}", errorResponse);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatusCode, webRequest);
    }

    @ExceptionHandler(RecommendedWineNotFoundException.class)
    public ResponseEntity<Object> handleRecommendedWineNotFoundException(RecommendedWineNotFoundException ex, WebRequest webRequest) {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .title(httpStatusCode.toString())
                .status(httpStatusCode.value())
                .detail(ex.getMessage())
                .build();
        log.error("Handler error recommended wine not found exception {}", errorResponse);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatusCode, webRequest);
    }
}
