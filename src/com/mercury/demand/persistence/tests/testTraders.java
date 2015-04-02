package com.mercury.demand.persistence.tests;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mercury.demand.persistence.model.Traders;


public class testTraders {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		//the user in in transient status
		Traders trader1 = new Traders("lily1", "123456","xiaoliyuan0311@gmail.com",5000.0,"A");
		session.save(trader1);
		tx.commit();
		
		String hql = "from Traders";
		Query query = session.createQuery(hql);	
		System.out.println("******************");
		List<Traders> list = query.list();
		for(Traders pl:list){
			System.out.println(pl);	
		}
		HibernateUtil.closeSession();

	}

}
