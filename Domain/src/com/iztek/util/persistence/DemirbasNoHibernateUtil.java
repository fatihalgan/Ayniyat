package com.iztek.util.persistence;

import org.hibernate.Session;

public class DemirbasNoHibernateUtil {

	public static Session getSession() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session;
	}
}
