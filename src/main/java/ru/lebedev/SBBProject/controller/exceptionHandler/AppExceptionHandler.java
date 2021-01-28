package ru.lebedev.SBBProject.controller.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String pageNotFoundException() {
        return "404";
    }

    @ExceptionHandler(Exception.class)
    public String appExceptions() {
        return "500";
    }
}
