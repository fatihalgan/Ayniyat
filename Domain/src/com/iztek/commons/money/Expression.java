/*
 * Created on 01.Oca.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.commons.money;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Expression {
	
	public Money reduce(Bank bank, String to);
	
	public Expression plus(Expression addend);
	
	public Expression times(double multiplier);

}
