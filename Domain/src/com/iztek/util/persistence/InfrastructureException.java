/*
 * Created on 11.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.util.persistence;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InfrastructureException extends RuntimeException {

	/**
	 * 
	 */
	public InfrastructureException() {
		super();
	}

	/**
	 * @param message
	 */
	public InfrastructureException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InfrastructureException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public InfrastructureException(Throwable cause) {
		super(cause);
	}

}
