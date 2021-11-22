package com.pedrodlf.iobuilders.bank.user.adapter.rest;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pedrodlf.iobuilders.bank.user.exception.UserNotFoundException;
import com.pedrodlf.iobuilders.model.ErrorResponse;

@ControllerAdvice
public class UserExceptionHandler {
	
	Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);
	
	@ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
	   ErrorResponse response = new ErrorResponse();
	   response.setStatus(new BigDecimal(404));
	   response.setMessage("El usuario no existe");
	   response.setTimestamp(LocalTime.now().toString());
	   log.warn("El usuario no existe");
       return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
	   ErrorResponse response = new ErrorResponse();
	   response.setStatus(new BigDecimal(400));
	   response.setMessage(ex.getLocalizedMessage()+" El correo ya existe");
	   response.setTimestamp(LocalTime.now().toString());
	   log.warn(ex.getMessage());
       return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
    }

}
