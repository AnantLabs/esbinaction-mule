package esb.chapter11.scubadiving.model;

import java.io.Serializable;
import java.util.Date;

public class ScubaDivingRequest implements Serializable {
	
	private static final long serialVersionUID = -4126488536707347253L;
	
	private String guestName;
	private int roomNumber;
	private Date requestDate;
	private int numberOfDivers;
	private boolean lunchIncluded;
	private DivingInterest interest;
	
	public boolean isLunchIncluded() {
		return lunchIncluded;
	}
	public void setLunchIncluded(boolean lunchIncluded) {
		this.lunchIncluded = lunchIncluded;
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
	public DivingInterest getInterest() {
		return interest;
	}
	public void setInterest(DivingInterest interest) {
		this.interest = interest;
	}
}
