/**
 * 
 */
package com.demo.lombok.withlombok.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.lombok.withlombok.exception.UserException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author resulav
 *
 */
@RestControllerAdvice
@Slf4j
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * handles HTTP SERVICE_UNAVAILABLE case
	 * 
	 * @param e the exception as cause
	 */
	@ExceptionHandler(UserException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	protected void handleUserException(UserException e) {
		log.error("HttpStatus.SERVICE_UNAVAILABLE", e);
	}

	/**
	 * handles HTTP BAD_REQUEST case
	 * 
	 * @param e the exception as cause
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected void handleRuntimeException(IllegalArgumentException e) {
		log.error("HttpStatus.BAD_REQUEST", e);
	}

	/**
	 * handles HTTP BAD_REQUEST case
	 * 
	 * @param e the exception as cause
	 */
	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected void handleRuntimeException(IllegalStateException e) {
		log.error("HttpStatus.BAD_REQUEST", e);
	}

	/**
	 * handles HTTP INTERNAL_SERVER_ERROR case
	 * 
	 * @param e the exception as cause
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected void handle(Exception e) {
		log.error("HttpStatus.INTERNAL_SERVER_ERROR", e);
	}

}
