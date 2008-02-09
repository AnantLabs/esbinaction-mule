package esb.chapter11.scubadiving.model;

import java.io.Serializable;
import java.util.Date;

public class TaxiBooking implements Serializable {
	
	private static final long serialVersionUID = -7317307864053002816L;
	private long taxiBookingID;
	private double price;
	private Date taxiDepartureTime;
	private Date taxiReturnTime;

	public long getTaxiBookingID() {
		return taxiBookingID;
	}

	public void setTaxiBookingID(long taxiBookingID) {
		this.taxiBookingID = taxiBookingID;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
}
