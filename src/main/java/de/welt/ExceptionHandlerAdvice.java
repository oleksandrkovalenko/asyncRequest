package de.welt;

import de.welt.exception.UserDataExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UserDataExecutionException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public void userDataExecutionException(UserDataExecutionException e) {
    }

}
