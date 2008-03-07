package esb.chapter11.scubadiving.model;

import java.io.Serializable;
import java.util.Date;

public class DivingBooking implements Serializable {

	private static final long serialVersionUID = -1753194693313597308L;
	private long divingBookID;
	private Date divingEndTime;
	private Date divingStartingTime;
	private String instructorName;
	private String address;
	private String name;
	private double price;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getDivingBookID() {
		return divingBookID;
	}
	public void setDivingBookID(long divingBookID) {
		this.divingBookID = divingBookID;
	}
	public Date getDivingEndTime() {
		return divingEndTime;
	}
	public void setDivingEndTime(Date divingEndTime) {
		this.divingEndTime = divingEndTime;
	}
	public Date getDivingStartingTime() {
		return divingStartingTime;
	}
	public void setDivingStartingTime(Date divingStartingTime) {
		this.divingStartingTime = divingStartingTime;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
