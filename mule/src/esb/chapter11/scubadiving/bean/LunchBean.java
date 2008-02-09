package esb.chapter11.scubadiving.bean;

import org.apache.log4j.Logger;

import esb.chapter11.scubadiving.model.LunchBooking;

public class LunchBean {
	
	private final static Logger logger = Logger.getLogger(LunchBean.class);
	
	public void processLunch(LunchBooking lunchBooking) {
		logger.info("booked " + lunchBooking.getNumberOfLunches() + " lunches for " 
				+ lunchBooking.getGuestName() + " sleeping in room " + lunchBooking.getRoomNumber()
				+ " on day " + lunchBooking.getLunchDate());
	}

}
