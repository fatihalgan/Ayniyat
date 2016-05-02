package com.iztek.commons.money.test;

import java.math.BigDecimal;

import com.iztek.commons.money.*;
import junit.framework.TestCase;

/**
 * @author Cagdas CIRIT
 */
public class MoneyTest extends TestCase {

	public static void main(String[] args) {
		junit.swingui.TestRunner.run(MoneyTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public MoneyTest(String arg0) {
		super(arg0);
	}
	
	public void testMultiplication() {
		Money five = Money.dollar(5);
		assertEquals(Money.dollar(11), five.times( 2.2));
		assertEquals(Money.dollar(new BigDecimal(15)), five.times(3));
	}
	
	public void testFrancMultiplication() {
		Money five = Money.franc(5);
		assertEquals(Money.franc(11), five.times( 2.2));
		assertEquals(Money.franc(15), five.times(3));
	}
	
	public void testEquality() {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(5).equals(Money.dollar(6)));
		assertTrue(Money.franc(5).equals(Money.franc(5)));
		assertFalse(Money.franc(5).equals(Money.franc(6)));
		assertFalse(Money.franc(5).equals(Money.dollar(5)));
	}
	
	public void testCurrency() {
		assertEquals(Money.USD, Money.dollar(1).getCurrency());
		assertEquals(Money.CHF, Money.franc(1).getCurrency());
		assertEquals(Money.TL, Money.turkLirasi(1).getCurrency());
		assertEquals(Money.YTL, Money.yeniTurkLirasi(1).getCurrency());
	}
	
	public void testDifferentClassEquality() {
		assertTrue(Money.turkLirasi(10).equals(Money.turkLirasi(10)));
	}
	
	public void testPlusReturnsSum() {
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Money result = bank.reduce(sum, Money.USD);
		assertEquals(Money.dollar(7), result);
	}
	
	public void testReduceMoney() {
		Bank bank = new Bank();
		Money result = bank.reduce(Money.dollar(1), Money.USD);
		assertEquals(Money.dollar(1), result);
	}
	
	public void testReduceMoneyDifferentCurrency() {
		Bank bank = new Bank();
		bank.addRate(Money.CHF, Money.USD, 2);
		Money result = bank.reduce(Money.franc(2), Money.USD);
		assertEquals(Money.dollar(1), result);
		result = bank.reduce(Money.turkLirasi(5500000), Money.YTL);
		assertEquals(Money.yeniTurkLirasi(5.5), result);
		result = bank.reduce(Money.yeniTurkLirasi(4), Money.TL);
		assertEquals(Money.turkLirasi(4000000), result);
	}
	
	public void testIdentityRate() {
		assertTrue(new Bank().rate(Money.USD, Money.USD)==1.0);
		assertTrue(new Bank().rate(Money.YTL, Money.TL)== 0.000001);
		assertTrue(new Bank().rate(Money.TL, Money.YTL)==1000000);
	}
	
	public void testMixedAddition() {
		Expression ytl = Money.yeniTurkLirasi(5);
		Expression tl = Money.turkLirasi(1500000);
		Bank bank = new Bank();
		Money result = bank.reduce(ytl.plus(tl), Money.YTL);
		assertEquals(Money.yeniTurkLirasi( 6.5), result);
		result = bank.reduce(tl.plus(ytl), Money.TL);
		assertEquals(Money.turkLirasi(6500000), result);
	}
	
	public void testSumPlusMoney() {
		Expression ytl = Money.yeniTurkLirasi(5);
		Expression tl = Money.turkLirasi(1500000);
		Bank bank = new Bank();
		Expression sum = new Sum(ytl, tl).plus(ytl);
		Money result = bank.reduce(sum, Money.YTL);
		assertEquals(Money.yeniTurkLirasi(11.5), result);
		result = bank.reduce(sum, Money.TL);
		assertEquals(Money.turkLirasi(11500000), result);
	}
	
	public void testSumTimes() {
		Expression ytl = Money.yeniTurkLirasi(5);
		Expression tl = Money.turkLirasi(1500000);
		Bank bank = new Bank();
		Expression sum = new Sum(ytl, tl).times(2);
		Money result = bank.reduce(sum, Money.YTL);
		assertEquals(Money.yeniTurkLirasi(13), result);
		result = bank.reduce(sum, Money.TL);
		assertEquals(Money.turkLirasi(13000000), result);
	}
	
}
