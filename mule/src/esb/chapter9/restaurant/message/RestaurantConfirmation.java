package esb.chapter9.restaurant.message;

import java.io.Serializable;

public class RestaurantConfirmation implements Serializable {

	private static final long serialVersionUID = -6683752719846780901L;
	private String guestName;
	private int roomNumber;
	
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
}
