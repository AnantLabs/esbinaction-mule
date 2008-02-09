package esb.chapter11.scubadiving.model;

import java.io.Serializable;
import java.util.Date;

public class TaxiRequest implements Serializable {
	
	private static final long serialVersionUID = -6523885465303043648L;
	private Date departureTime;
	private Date returnTime;
	private String destinationAddress;
	private String hotelName;
	private int numberOfTravelers;
	
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public int getNumberOfTravelers() {
		return numberOfTravelers;
	}
	public void setNumberOfTravelers(int numberOfTravelers) {
		this.numberOfTravelers = numberOfTravelers;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
}
