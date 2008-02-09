package esb.chapter11.scubadiving.model;

import java.io.Serializable;
import java.util.Date;

public class ScubaDivingBooking implements Serializable {

	private static final long serialVersionUID = -4549349060174855740L;
	private long divingBookingID;
	private long taxiBookingID;
	private String schoolName;
	private String schoolAddress;
	private String instructorName;
	private Date taxiDepartureTime;
	private Date divingStartingTime;
	private Date divingEndTime;
	private Date taxiReturnTime;
	private double totalPrice;
	
	public long getDivingBookingID() {
		return divingBookingID;
	}
	public void setDivingBookingID(long divingBookingID) {
		this.divingBookingID = divingBookingID;
	}
	public long getTaxiBookingID() {
		return taxiBookingID;
	}
	public void setTaxiBookingID(long taxiBookingID) {
		this.taxiBookingID = taxiBookingID;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public Date getTaxiDepartureTime() {
		return taxiDepartureTime;
	}
	public void setTaxiDepartureTime(Date taxiDepartureTime) {
		this.taxiDepartureTime = taxiDepartureTime;
	}
	public Date getTaxiReturnTime() {
		return taxiReturnTime;
	}
	public void setTaxiReturnTime(Date taxiReturnTime) {
		this.taxiReturnTime = taxiReturnTime;
	}
	public Date getDivingStartingTime() {
		return divingStartingTime;
	}
	public void setDivingStartingTime(Date divingStartingTime) {
		this.divingStartingTime = divingStartingTime;
	}
	public Date getDivingEndTime() {
		return divingEndTime;
	}
	public void setDivingEndTime(Date divingEndTime) {
		this.divingEndTime = divingEndTime;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
