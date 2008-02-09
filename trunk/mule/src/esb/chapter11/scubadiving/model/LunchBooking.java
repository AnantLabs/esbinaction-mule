package esb.chapter11.scubadiving.model;

import java.io.Serializable;
import java.util.Date;


public class LunchBooking implements Serializable {

	private static final long serialVersionUID = -158842992812756930L;
	private String guestName;
	private int roomNumber;
	private int numberOfLunches;
	private Date lunchDate;
	
	public int getNumberOfLunches() {
		return numberOfLunches;
	}
	public void setNumberOfLunches(int numberOfLunches) {
		this.numberOfLunches = numberOfLunches;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getLunchDate() {
		return lunchDate;
	}
	public void setLunchDate(Date lunchDate) {
		this.lunchDate = lunchDate;
	}
}
