package com.ets.r2dbc.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExceptionHandler(Exception ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({JsonMappingException.class, HttpMessageNotReadableException.class, 
		MethodArgumentNotValidException.class, UnrecognizedPropertyException.class, 
		ResponseStatusException.class, MethodArgumentNotValidException.class,MissingServletRequestPartException.class})
	public ResponseEntity<?> badRequestException(Exception ex, WebRequest request){
		System.out.println("BadRequestException called...!");
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
}