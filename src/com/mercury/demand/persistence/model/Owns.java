package com.mercury.demand.persistence.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="owns")
public class Owns implements java.io.Serializable {

	private static final long serialVersionUID = -1172947391039954142L;

	@EmbeddedId	
	private User_Stock id;
	
	@Column
	private int quantity;

	public Owns(){}
	public Owns(User_Stock id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}
	
	
	public User_Stock getId() {
		return id;
	}
	public void setId(User_Stock id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
