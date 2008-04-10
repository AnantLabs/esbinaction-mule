package esb.chapter9.restaurant.persist;

import org.mule.RequestContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;

import esb.chapter9.restaurant.dao.ReservationDAOIF;
import esb.chapter9.restaurant.message.RestaurantConfirmation;
import esb.chapter9.restaurant.model.Reservation;

public class ConfirmationBean {
	
	private ReservationDAOIF reservationDAO;

	public RestaurantConfirmation loadConfirmation(String restaurantChoice) {
		MuleEventContext context = RequestContext.getEventContext();
		MuleMessage message = context.getMessage();
		long reservationID = message.getLongProperty(ExpirationBean.RESPONSE_ID, -1);
		Reservation reservation = reservationDAO.getReservation(reservationID);
		RestaurantConfirmation confirmation = new RestaurantConfirmation();
		confirmation.setGuestName(reservation.getGuestName());
		confirmation.setRoomNumber(reservation.getRoomNumber());
		message.setStringProperty("choice", restaurantChoice);
		return confirmation;
	}
	
	public void setReservationDAO(ReservationDAOIF reservationDAO) {
		this.reservationDAO = reservationDAO;
	}
}
