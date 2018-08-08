/**
 * 
 */
package com.demo.lombok.withoutlombok.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.lombok.withoutlombok.exception.UserException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author resulav
 *
 */
@RestControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(UserControllerAdvice.class);

	/**
	 * handles HTTP SERVICE_UNAVAILABLE case
	 * 
	 * @param e the exception as cause
	 */
	@ExceptionHandler(UserException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	protected void handleUserException(UserException e) {
		LOG.error("HttpStatus.SERVICE_UNAVAILABLE", e);
	}
	
	
	/**
	 * handles HTTP BAD_REQUEST case
	 * 
	 * @param e the exception as cause
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected void handleRuntimeException(IllegalArgumentException e) {
		LOG.error("HttpStatus.BAD_REQUEST", e);
	}
	
	
	/**
	 * handles HTTP BAD_REQUEST case
	 * 
	 * @param e the exception as cause
	 */
	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected void handleRuntimeException(IllegalStateException e) {
		LOG.error("HttpStatus.BAD_REQUEST", e);
	}

	/**
	 * handles HTTP INTERNAL_SERVER_ERROR case
	 * 
	 * @param e the exception as cause
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected void handle(Exception e) {
		LOG.error("HttpStatus.INTERNAL_SERVER_ERROR", e);
	}

}
