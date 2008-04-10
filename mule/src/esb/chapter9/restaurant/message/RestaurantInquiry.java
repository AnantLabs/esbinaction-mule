package esb.chapter9.restaurant.message;

import java.io.Serializable;
import java.util.Date;

public class RestaurantInquiry implements Serializable {

	private static final long serialVersionUID = -4546768116622879476L;
	private String guestName;
	private int persons;
	private Date reservationTime;
	private int roomNumber;
		
	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public Date getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String toString() {
	   final String TAB = "    ";
	   String retValue = "RestaurantInquiry ( "
	        + super.toString() + TAB
	        + "guestName = " + this.guestName + TAB
	        + "persons = " + this.persons + TAB
	        + "roomNumber = " + this.roomNumber
	        + " )"; 
	    return retValue;
	}
}
