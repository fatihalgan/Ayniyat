/*
 * Created on 01.Oca.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.commons.money;

import java.math.BigDecimal;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Sum implements Expression {

	private Expression augend;
	private Expression addend;
	
	/**
	 * 
	 */
	public Sum(Expression augend, Expression addend) {
		super();
		this.augend = augend;
		this.addend = addend;
	}
	
	
	/**
	 * @return Returns the addend.
	 */
	public Expression getAddend() {
		return addend;
	}
	/**
	 * @return Returns the augend.
	 */
	public Expression getAugend() {
		return augend;
	}
	
	public Money reduce(Bank bank, String to) {
		BigDecimal amount = new BigDecimal(augend.reduce(bank, to).amount.doubleValue() + addend.reduce(bank, to).amount.doubleValue());
		return new Money(amount, to);
	}
	
	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}
	
	public Expression times(double multiplier) {
		return new Sum(augend.times(multiplier), addend.times(multiplier));
	}
}
