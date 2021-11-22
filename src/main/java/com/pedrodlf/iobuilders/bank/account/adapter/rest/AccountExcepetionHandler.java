package com.pedrodlf.iobuilders.bank.account.adapter.rest;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pedrodlf.iobuilders.bank.exceptions.AccountNotFoundException;
import com.pedrodlf.iobuilders.bank.exceptions.UserNotFoundException;
import com.pedrodlf.iobuilders.model.ErrorResponse;


@ControllerAdvice
public class AccountExcepetionHandler {

	Logger log = LoggerFactory.getLogger(AccountExcepetionHandler.class);
	
	
	@ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
	   ErrorResponse response = new ErrorResponse();
	   response.setStatus(new BigDecimal(404));
	   response.setMessage("La cuenta no existe");
	   response.setTimestamp(LocalTime.now().toString());
	   log.warn(response.getMessage());
       return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);
    }
	
	
}
