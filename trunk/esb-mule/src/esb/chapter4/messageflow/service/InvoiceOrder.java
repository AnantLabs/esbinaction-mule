package esb.chapter4.messageflow.service;

import java.io.Serializable;

import esb.chapter4.messageflow.Order;

public class InvoiceOrder extends Order implements Serializable {
	
	private static final long serialVersionUID = 2364718135209443776L;
	private String clientname;
	private String address;
	private String city;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
}