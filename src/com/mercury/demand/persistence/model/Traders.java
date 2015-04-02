package com.mercury.demand.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.JoinColumn;


@Entity
@Table(name="traders")
public class Traders implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2687172624990746379L;
	private String username;
	private String password;
	private String email;
	private double amount;
	private String authority;
	private Set<Stocks> stock = new HashSet<Stocks>();
	private Set<Transactions> tran = new HashSet<Transactions>();
	
	public Traders(){}

	public Traders(String username, String password, String email,
			double amount, String authority, Set<Stocks> stock,
			Set<Transactions> tran) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.amount = amount;
		this.authority = authority;
		this.stock = stock;
		this.tran = tran;
	}


	public Traders(String username, String password, String email,
			double amount, String authority) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.amount = amount;
		this.authority = authority;
	}
	@Id
    @Column(name="username", nullable = false)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

    @Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    @Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Column
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

    @Column
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
    @ManyToMany(fetch = FetchType.LAZY)
   /*@JoinTable(name = "owns", joinColumns = {
    		@JoinColumn(name = "username") },
    		inverseJoinColumns = { @JoinColumn(name = "companyname")})*/

	public Set<Stocks> getStock() {
		return stock;
	}

	public void setStock(Set<Stocks> stock) {
		this.stock = stock;
	}

	@OneToMany(mappedBy = "trader", cascade = CascadeType.REMOVE)	
	public Set<Transactions> getTran() {
		return tran;
	}
	public void setTran(Set<Transactions> tran) {
		this.tran = tran;
	}
	public String toString(){
		return username+"\t"+ "Nice to meet you!";
	}

}
