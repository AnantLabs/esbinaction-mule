package esb.chapter9.restaurant.model;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
	
	private static final long serialVersionUID = 6701035968120431419L;
	private Date currentTime;
	private String guestName;
	private Integer numberOfPersons;
	private Long reservationID;
	private Date reservationTime;
	private Restaurant restaurant;
	private Integer roomNumber;
	
	public Reservation() {
		this.currentTime = new Date();
	}

	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}
	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public Long getReservationID() {
		return reservationID;
	}
	public void setReservationID(Long reservationID) {
		this.reservationID = reservationID;
	}

	public Date getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRestaurantName(String name) {
		if(restaurant == null) {
			restaurant = new Restaurant();
		}
		restaurant.setName(name);
	}
}
