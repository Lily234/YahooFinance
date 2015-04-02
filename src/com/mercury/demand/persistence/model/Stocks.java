package com.mercury.demand.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="stocks")
public class Stocks implements java.io.Serializable {
	
	private static final long serialVersionUID = 4069666588214721843L;
	private String companyname;
	private int numberofavailable;
	private Set<Traders> trader = new HashSet<Traders>();
	private Set<Transactions> tran = new HashSet<Transactions>();
	
	public Stocks(){}

	public Stocks(String companyname, int numberofavailable,
			Set<Traders> trader, Set<Transactions> tran) {
		super();
		this.companyname = companyname;
		this.numberofavailable = numberofavailable;
		this.trader = trader;
		this.tran = tran;
	}


	public Stocks(String companyname, int numberofavailable) {
		super();
		this.companyname = companyname;
		this.numberofavailable = numberofavailable;
	}
	
	
	@Id
	@Column(name="companyname", nullable = false)
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Column
	public int getNumberofavailable() {
		return numberofavailable;
	}

	public void setNumberofavailable(int numberofavailable) {
		this.numberofavailable = numberofavailable;
	}
	
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "stock")
	public Set<Traders> getTrader() {
		return trader;
	}

	public void setTrader(Set<Traders> trader) {
		this.trader = trader;
	}

	@OneToMany(mappedBy = "stock",cascade = CascadeType.REMOVE)
	public Set<Transactions> getTran() {
		return tran;
	}
	public void setTran(Set<Transactions> tran) {
		this.tran = tran;
	}
	public String toString(){
		return companyname + "\t" +  "has " + numberofavailable + " left";
	}

}
