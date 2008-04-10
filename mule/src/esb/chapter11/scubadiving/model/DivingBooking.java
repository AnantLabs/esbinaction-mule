package esb.chapter11.scubadiving.model;

import java.io.Serializable;
import java.util.Date;

public class DivingBooking implements Serializable {

	private static final long serialVersionUID = -1753194693313597308L;
	private long bookingID;
	private Date endTime;
	private Date startTime;
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
	public long getBookingID() {
		return bookingID;
	}
	public void setBookingID(long bookingID) {
		this.bookingID = bookingID;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
