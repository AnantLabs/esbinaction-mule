package esb.chapter9.restaurant.persist;

import org.mule.RequestContext;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;

import esb.chapter9.restaurant.dao.ReservationDAOIF;
import esb.chapter9.restaurant.message.RestaurantResponse;
import esb.chapter9.restaurant.model.Reservation;

public class ExpirationBean {
	
	public static final String RESPONSE_ID = "restaurantResponseID";
	private ReservationDAOIF reservationDAO;

	public RestaurantResponse persist(RestaurantResponse response) {
		Reservation reservation = new Reservation();
		reservation.setRestaurantName(response.getRestaurant());
		reservation.setGuestName(response.getInquiry().getGuestName());
		reservation.setRoomNumber(response.getInquiry().getRoomNumber());
		reservation.setReservationTime(response.getInquiry().getReservationTime());
		reservation.setNumberOfPersons(response.getInquiry().getPersons());
		reservation = reservationDAO.saveReservation(reservation);
		MuleEventContext context = RequestContext.getEventContext();
		MuleMessage message = context.getMessage();
		message.setLongProperty(RESPONSE_ID, reservation.getReservationID());
		return response;
	}
	
	public void setReservationDAO(ReservationDAOIF reservationDAO) {
		this.reservationDAO = reservationDAO;
	}
}
