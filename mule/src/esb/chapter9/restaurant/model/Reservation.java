package esb.chapter9.restaurant.model;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
	
	private static final long serialVersionUID = 6701035968120431419L;
	private Long reservationID;
	private Restaurant restaurant;
	private Date currentTime;
	
	public Reservation() {
		this.currentTime = new Date();
	}
	
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	public Long getReservationID() {
		return reservationID;
	}
	public void setReservationID(Long reservationID) {
		this.reservationID = reservationID;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public void setRestaurantName(String name) {
		if(restaurant == null) {
			restaurant = new Restaurant();
		}
		restaurant.setName(name);
	}

}
