package esb.chapter9.restaurant.router;

import org.apache.log4j.Logger;
import org.mule.api.MuleMessage;
import org.mule.api.routing.filter.Filter;

import esb.chapter9.restaurant.dao.ReservationDAOIF;
import esb.chapter9.restaurant.model.Reservation;

/**
 * Simple mule filter that checks whether a message is received within
 * a certain amount of time.
 * 
 */
public class ExpirationCheckFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(ExpirationCheckFilter.class);
	private ReservationDAOIF reservationDAO;
	
	public boolean accept(MuleMessage message) {
		long reservationID = message.getLongProperty(ExpirationRouter.RESPONSE_ID, -1);
		if(reservationID == -1) {
			logger.error("received message without a " + ExpirationRouter.RESPONSE_ID + " header property");
		}
		Reservation reservation = reservationDAO.getReservation(reservationID);
		if(reservation == null || reservation.getReservationID() == null) return false;
		boolean timeout = isTimeout(reservation);
		
		if (timeout) {
			logger.info("reservation has timed out " + reservationID);
			return false;
		} else {
			return true;
		}
	}
	
	private boolean isTimeout(Reservation reservation) {
		long currentTime = System.currentTimeMillis();
		long timeoutValue = currentTime - reservation.getCurrentTime().getTime();
		if(timeoutValue > reservation.getRestaurant().getTimeout())
			return true;
		else
			return false;
		
	}

	public void setReservationDAO(ReservationDAOIF reservationDAO) {
		this.reservationDAO = reservationDAO;
	}
}
