package com.pedrodlf.iobuilders.bank.transaction.adapter.rest;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pedrodlf.iobuilders.bank.transaction.exception.NoEnoughFundsException;
import com.pedrodlf.iobuilders.model.ErrorResponse;

@ControllerAdvice
public class TransactionsExceptionHandler {
	
	Logger log = LoggerFactory.getLogger(TransactionsExceptionHandler.class);
	
	@ExceptionHandler(NoEnoughFundsException.class)
    protected ResponseEntity<ErrorResponse> handleNoEnoughFundsException(NoEnoughFundsException ex) {
	   ErrorResponse response = new ErrorResponse();
	   response.setStatus(new BigDecimal(400));
	   response.setMessage(ex.getMessage());
	   response.setTimestamp(LocalTime.now().toString());
	   log.warn(ex.getMessage());
       return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
    }

}
