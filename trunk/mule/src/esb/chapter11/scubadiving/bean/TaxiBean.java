package esb.chapter11.scubadiving.bean;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.log4j.Logger;

import esb.chapter11.scubadiving.model.TaxiBooking;
import esb.chapter11.scubadiving.model.TaxiRequest;

public class TaxiBean {
	
	private final static Logger logger = Logger.getLogger(TaxiBean.class);
	private double pricePerPerson;

	public TaxiBooking processTaxiRequest(TaxiRequest request) {
		if(logger.isInfoEnabled()) {
			logger.info("Taxi service received a request");
		}
		TaxiBooking booking = new TaxiBooking();
		booking.setTaxiBookingID(new Random().nextInt(Integer.MAX_VALUE));
		booking.setPrice(request.getNumberOfTravelers() * pricePerPerson);
		GregorianCalendar departureTime = new GregorianCalendar();
		departureTime.setTime(request.getDepartureTime());
		departureTime.set(Calendar.HOUR_OF_DAY, departureTime.get(Calendar.HOUR_OF_DAY) - 2);
		booking.setTaxiDepartureTime(departureTime.getTime());
		GregorianCalendar returnTime = new GregorianCalendar();
		returnTime.setTime(request.getReturnTime());
		returnTime.set(Calendar.HOUR_OF_DAY, returnTime.get(Calendar.HOUR_OF_DAY) + 1);
		booking.setTaxiReturnTime(returnTime.getTime());
		return booking;
	}

	public double getPricePerPerson() {
		return pricePerPerson;
	}

	public void setPricePerPerson(double pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}
}
