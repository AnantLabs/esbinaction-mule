package esb.chapter4.messageflow.domain;

import java.io.Serializable;

public class BookQuote implements Serializable {
	
	private static final long serialVersionUID = -3324996905858510986L;
	private String companyName;
	private String isbn;
	private float price;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
