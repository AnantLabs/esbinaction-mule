package esb.chapter4.messageflow;

import java.io.Serializable;

public class Order implements Serializable {
	
	private static final long serialVersionUID = -3324996905858510986L;
	protected Long orderID;
	protected Float amount;
	protected String orderDescription;
	protected String clientNumber;
	
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public Long getOrderID() {
		return orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
}
