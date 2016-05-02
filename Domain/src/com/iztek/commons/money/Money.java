/*
 * Created on 31.Ara.2004
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
public class Money implements Expression {
	public static final String TL = "TL";
	public static final String YTL = "YTL";
	public static final String USD = "USD";
	public static final String CHF = "CHF";
	
	public static final int SCALE = 2; //noktadan sonra 2 hassasiyet para için uygun.
	
	protected BigDecimal amount;
	protected String currency;
	
	public Money(){	}
	
	public Money(BigDecimal amount, String currency) {
		this.amount = amount.setScale(SCALE,BigDecimal.ROUND_HALF_EVEN);
		this.currency = currency;
	}
	
	public Money(double amount, String currency) {
		this.amount = new BigDecimal(amount).setScale(SCALE,BigDecimal.ROUND_HALF_EVEN);
		this.currency = currency;
	}
	
	/**
	 * @return Returns the amount.
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Expression times(double multiplier) {
		return new Money(new BigDecimal(amount.doubleValue() * multiplier), currency);
	}
	
	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		Money money = (Money)o;
		return amount.compareTo(money.getAmount())==0 &&
			getCurrency().equals(money.getCurrency());
	}
	
	public String toString() {
		return amount + " " + currency;
	}
	
	public static Money franc(BigDecimal amount) {
		return new Money(amount, CHF);
	}
	
	public static Money dollar(BigDecimal amount) {
		return new Money(amount, USD);
	}
	
	public static Money turkLirasi(BigDecimal amount) {
		return new Money(amount, TL);
	}
	
	public static Money yeniTurkLirasi(BigDecimal amount) {
		return new Money(amount, YTL);
	}
	
	public static Money franc(double amount) {
		return new Money(amount, CHF);
	}
	
	public static Money dollar(double amount) {
		return new Money(amount, USD);
	}
	
	public static Money turkLirasi(double amount) {
		return new Money(amount, TL);
	}
	
	public static Money yeniTurkLirasi(double amount) {
		return new Money(amount, YTL);
	}
	
	public Money reduce(Bank bank, String to) {
		double rate = bank.rate(currency, to);
		return new Money(new BigDecimal(amount.doubleValue()/rate), to);
	}
	
	public int hashcode() {
		return (int) (29 * amount.doubleValue() + currency.hashCode());
	}
}
