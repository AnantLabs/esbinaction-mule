package esb.chapter9.restaurant.message;

import java.io.Serializable;

public class RestaurantInquiry implements Serializable {

	private static final long serialVersionUID = -4546768116622879476L;
	
	private String reservationTime;
	private int persons;
	
	public RestaurantInquiry() {
	}
	
	public RestaurantInquiry(String reservationTime, int persons) {
		this.reservationTime = reservationTime;
		this.persons = persons;
	}
	
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	public String getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	
	public String toString() {
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "RestaurantInquiry ( "
	        + super.toString() + TAB
	        + "time = " + this.reservationTime + TAB
	        + "persons = " + this.persons + TAB
	        + " )";
	    
	    return retValue;
	}

	
	
	
}
