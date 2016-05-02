/*
 * Created on 01.Oca.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.commons.money;

import java.util.Hashtable;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Bank {
	public static final double YTL_TL_RATE = 0.000001;
	public static final double TL_YTL_RATE = 1000000; 
	
	private Hashtable rates = new Hashtable();
	/**
	 * 
	 */
	public Bank() {
		addRate(Money.YTL,Money.TL,YTL_TL_RATE);
		addRate(Money.TL,Money.YTL,TL_YTL_RATE);
	}
	
	public Money reduce(Expression source, String to) {
		return source.reduce(this, to);
	}
	
	public void addRate(String from, String to, double rate) {
		rates.put(new Pair(from, to), new Double(rate));
	}
	
	public double rate(String from, String to) {
		if(from.equals(to)) return (double) 1.0;
		Double rate = (Double)rates.get(new Pair(from, to));
		return rate.doubleValue();
	}
	
	private class Pair {
		private String from;
		private String to;
		
		Pair(String from, String to) {
			this.from = from;
			this.to = to;
		}
		
		public boolean equals(Object o) {
			Pair pair = (Pair)o;
			return from.equals(pair.from) && to.equals(pair.to);
		}
		
		public int hashCode() {
			return 0;
		}
	}

}
