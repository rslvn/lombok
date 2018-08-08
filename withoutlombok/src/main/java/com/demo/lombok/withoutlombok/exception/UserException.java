/**
 * 
 */
package com.demo.lombok.withoutlombok.exception;

/**
 * @author resulav
 *
 */
public class UserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1092145758908758373L;

	/**
	 * @param message
	 * @param cause
	 */
	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UserException(String message) {
		super(message);
	}
	
}
