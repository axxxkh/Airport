package flightMicroService.exceptions;

import jdk.jfr.StackTrace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionControllerAdvise {


    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value= { EntityNotFoundException.class })
    protected EntityNotFoundException handleConflict(EntityNotFoundException exception, HttpServletResponse response) {
        return exception;
    }
}
