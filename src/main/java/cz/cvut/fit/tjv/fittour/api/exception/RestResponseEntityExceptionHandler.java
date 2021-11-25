package cz.cvut.fit.tjv.fittour.api.exception;

import cz.cvut.fit.tjv.fittour.business.EntityStateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{

    //Handle exception from business
    @ExceptionHandler(value = {EntityStateException.class})
    protected ResponseEntity<Object> handleEntityState(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Entity not unique";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    //Handle exception from snowboard id creation
    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<Object> handleNullPointerException(
        RuntimeException ex, WebRequest request)
    {
        String bodyOfResponse = "ID cannot be nullable!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    //Handle newly created REST API exception when object is not found
    @ExceptionHandler(NoEntityFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(
            NoEntityFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UpdatedIDException.class})
    protected ResponseEntity<Object> handleUpdatedIDException(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ExpectedNullIDException.class})
    protected ResponseEntity<Object> handleExpectedNullIDException(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



}
