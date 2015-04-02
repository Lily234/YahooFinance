package com.mercury.demand.persistence.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="transactions")
public class Transactions implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 784209935475804333L;
	private int transid;
	private String username;
	private String companyname;
	private String buyorsell;
	private int quantity;
	private Date transtime;
	private Traders trader;
	private Stocks stock;
	
	public Transactions(){}
	
	public Transactions(int transid, String username, String companyname,
			String buyorsell, int quantity, Date transtime) {
		super();
		this.transid = transid;
		this.username = username;
		this.companyname = companyname;
		this.buyorsell = buyorsell;
		this.quantity = quantity;
		this.transtime = transtime;
	}

	public Transactions(int transid, String username, String companyname,
			String buyorsell, int quantity, Date transtime, Traders trader,
			Stocks stock) {
		super();
		this.transid = transid;
		this.username = username;
		this.companyname = companyname;
		this.buyorsell = buyorsell;
		this.quantity = quantity;
		this.transtime = transtime;
		this.trader = trader;
		this.stock = stock;
	}

	@Id
    @Column(name="transid", nullable = false)
	public int getTransid() {
		return transid;
	}
	public void setTransid(int transid) {
		this.transid = transid;
	}
	
	@Column
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	
	@Column
	public String getBuyorsell() {
		return buyorsell;
	}
	public void setBuyorsell(String buyorsell) {
		this.buyorsell = buyorsell;
	}
	
	@Column
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Column
	public Date getTranstime() {
		return transtime;
	}
	public void setTranstime(Date transtime) {
		this.transtime = transtime;
	}


	@ManyToOne(cascade = CascadeType.ALL)
	public Traders getTrader() {
		return trader;
	}

	public void setTrader(Traders trader) {
		this.trader = trader;
	}

	
	@ManyToOne(cascade = CascadeType.ALL)
	public Stocks getStock() {
		return stock;
	}

	public void setStock(Stocks stock) {
		this.stock = stock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString(){
		return username + "\t" + buyorsell + "\t" + quantity + "\t" + companyname + "\t" + "stocks";
	} 
	

}
