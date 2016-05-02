package com.iztek.util.persistence.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iztek.util.persistence.DemirbasNoHibernateUtil;

import junit.framework.TestCase;

public class HibernateUtilTest extends TestCase {

	public static void main(String[] args) {
		junit.swingui.TestRunner.run(HibernateUtilTest.class);
	}
	
	public void testGetDemirbasNoGenConnection() {
		Session session = DemirbasNoHibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		tx.commit();
	}
}
