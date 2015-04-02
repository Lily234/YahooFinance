package com.mercury.demand.persistence.tests;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {
	private static final SessionFactory FACTORY;
	//only static block
	static {
		try {
			FACTORY = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	//have shallow copies so that can survive in multi-threading
	private static final ThreadLocal<Session> SESSION = new ThreadLocal<Session>() {
		@Override
		protected Session initialValue() {
			return FACTORY.openSession();
		}
	};
	

	public static SessionFactory getSessionFactory() {
		return FACTORY;
	}
	
	
	public static Session currentSession() throws HibernateException {
		Session s = SESSION.get();
		if (s==null) {
			s = FACTORY.openSession();
			SESSION.set(s);
		}
		return s;
	}
	
	public static void closeSession() throws HibernateException {
		Session s = SESSION.get();
		//this line is very important, to prevent a thread open session which has been closed already
		SESSION.set(null);
		if (s!=null) s.close();
	}
}
