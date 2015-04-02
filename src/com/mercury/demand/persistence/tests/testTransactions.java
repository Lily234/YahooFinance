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
		
		Session session = HibernateUtil.currentSession();
		session.clear();
		Transaction tx = session.beginTransaction();
		
		//the user in in transient status
		Traders trader1 = new Traders("lily1", "123456","xiaoliyuan0311@gmail.com",5000.0,"A");
		trader1.setAmount(4900.0);
		Stocks stock1 = new Stocks("company1", 2000);
		stock1.setNumberofavailable(1900);
		Transactions tran1 = new Transactions(3,"lily1","company1","S",10, new Date());
		tran1.setTrader(trader1);
		tran1.setStock(stock1);	
		session.save(tran1);
		System.out.println("test1!");
		
		try {
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		//tx.commit();
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
				if(buyorsell=="B"){
					int highQuantity = rs.getInt("quantity")+quantity;
					String sqlUpdate1 = "update owns set quantity=? where username=? AND companyname=?";
                    
					PreparedStatement st3 = conn.prepareStatement(sqlUpdate1);
					st3.setInt(1, highQuantity);
					st3.setString(2, username);
					st3.setString(3,companyname);
					st3.executeUpdate();
					
				}else{
					int lowerQuantity = rs.getInt("quantity")-quantity;
					if(lowerQuantity>=0){
						String sqlUpdate2 = "update owns set quantity=? where username=? AND companyname=?";
						PreparedStatement st4 = conn.prepareStatement(sqlUpdate2);
						st4.setInt(1, lowerQuantity);
						st4.setString(2, username);
						st4.setString(3, companyname);
						st4.executeUpdate();
						
					}else{
						System.out.println("Sorry, you do not have so many stocks of this company to sell!");
					}
				}
				
				
			} else if(buyorsell=="B")
			{
				
				System.out.println("You need to insert new item in owns!");
				String sqlInsert = "insert into owns (username, companyname, quantity) values (?,?,?)";
				PreparedStatement st2 = conn.prepareStatement(sqlInsert);
				st2.setString(1, username);
				st2.setString(2, companyname);
				st2.setInt(3, quantity);
				st2.executeUpdate();
			} else{
				System.out.println("Sorry, you do not have this kind of stocks for selling!");
			}
					
			rs.close();
		} catch (Exception e){
			e.printStackTrace();
		} 
		
	}

}
