package br.com.rafael.dsbootcampchallenger.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.rafael.dsbootcampchallenger.exception.DataBaseException;
import br.com.rafael.dsbootcampchallenger.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFoundException(ResourceNotFoundException e , HttpServletRequest req){
		StandardError std = new StandardError();
		std.setTimeStamp(Instant.now());
		std.setStatus(HttpStatus.NOT_FOUND.value());
		std.setError("Resoruce Not Found");
		std.setMessage(e.getMessage());
		std.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(std);
		
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBaseException(ResourceNotFoundException e , HttpServletRequest req){
		StandardError std = new StandardError();
		std.setTimeStamp(Instant.now());
		std.setStatus(HttpStatus.BAD_REQUEST.value());
		std.setError("Database Exception");
		std.setMessage(e.getMessage());
		std.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(std);
		
	}

}
