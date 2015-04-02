package com.mercury.demand.persistence.tests;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mercury.demand.persistence.model.Stocks;
import com.mercury.demand.persistence.model.Traders;
import com.mercury.demand.persistence.model.Transactions;
import java.sql.*;


public class testTransactions {


	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		//the user in in transient status
		Traders trader1 = new Traders("lily1", "123456","xiaoliyuan0311@gmail.com",5000.0,"A");
		trader1.setAmount(4700.0);
		Stocks stock1 = new Stocks("company1", 2000);
		stock1.setNumberofavailable(1200);
		Transactions tran1 = new Transactions(1,"lily1","company1","B",100, new Date());
		tran1.setTrader(trader1);
		tran1.setStock(stock1);	
		session.save(tran1);
		System.out.println("test1!");
		
		tx.commit();
		updateOwns(tran1.getUsername(), tran1.getCompanyname(), tran1.getBuyorsell(), tran1.getQuantity());
		System.out.println("test2!");
		
		//session.flush();		
		
		
		String hql = "from Transactions";
		Query query = session.createQuery(hql);	
		System.out.println("******************");
		List<Transactions> list = query.list();
		for(Transactions pl:list){
			System.out.println(pl);	
		}
		HibernateUtil.closeSession();

	}
	
	public static void updateOwns(String username, String companyname,String buyorsell, int quantity){
		
		System.out.println("The updateOwns method is working!");
	
		
		try{
			Connection conn = JdbcUtil.getConnection();
			
			//String sql = "select * from sample where name = '"name"'"
			
			String sql = "select * from owns where username=? AND companyname=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, companyname);
			
			//Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery();
			
			/*
			while(rs.next()){
				System.out.println(rs.getString("name") + "\t" + rs.getInt("age"));
			}
			*/
			
			if(rs.next()){
				System.out.println("You should update owns here!");
			} else if(buyorsell=="B")
			{
				String sqlInsert = "insert into owns (username, companyname, quantity) values (?,?,?)";
				st.setString(1, username);
				st.setString(2, companyname);
				st.setInt(3, quantity);
				st.executeUpdate(sqlInsert);
			} else{
				System.out.println("Sorry, you do not have this kind of stocks for selling!");
			}
					
			rs.close();
		} catch (Exception e){
			e.printStackTrace();
		} 
		
	}

}
