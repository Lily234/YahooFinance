package com.mercury.demand.persistence.tests;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mercury.demand.persistence.model.Stocks;


public class testStocks {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		//the user in in transient status
		Stocks stock1 = new Stocks("company1", 2000);
		session.save(stock1);
		tx.commit();
		
		String hql = "from Stocks";
		Query query = session.createQuery(hql);	
		System.out.println("******************");
		List<Stocks> list = query.list();
		for(Stocks pl:list){
			System.out.println(pl);	
		}
		HibernateUtil.closeSession();


	}

}
