package esb.chapter11.scubadiving.model;

import java.io.Serializable;
import java.util.Date;

public class DivingRequest implements Serializable {
	
	private static final long serialVersionUID = 2479981348258734152L;
	private Date requestDate;
	private int numberOfDivers;
	private String hotelName;
	private String bookingName;
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public int getNumberOfDivers() {
		return numberOfDivers;
	}
	public void setNumberOfDivers(int numberOfDivers) {
		this.numberOfDivers = numberOfDivers;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getBookingName() {
		return bookingName;
	}
	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}
}
