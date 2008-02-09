package esb.chapter9.restaurant.router;

import org.mule.api.MuleMessage;
import org.mule.api.MuleSession;
import org.mule.api.routing.RoutingException;
import org.mule.routing.outbound.FilteringOutboundRouter;

import esb.chapter9.restaurant.dao.ReservationDAOIF;
import esb.chapter9.restaurant.message.RestaurantResponse;
import esb.chapter9.restaurant.model.Reservation;

public class ExpirationRouter extends FilteringOutboundRouter {
	
	public static final String RESPONSE_ID = "restaurantResponseID";
	private ReservationDAOIF reservationDAO;

	/**
	 * We just add a timestamp and a ttl to the outgoing message. This 
	 * timestamp and ttl can be checked when a message is received to 
	 * check how much time it spent in transit.
	 */
	@Override
	public MuleMessage route(MuleMessage message, MuleSession session, boolean synchronous) throws RoutingException {
		RestaurantResponse restaurantResponse = (RestaurantResponse) message.getPayload();
		Reservation reservation = new Reservation();
		reservation.setRestaurantName(restaurantResponse.getRestaurant());
		reservation = reservationDAO.saveReservation(reservation);
		message.setLongProperty(RESPONSE_ID, reservation.getReservationID());
		return super.route(message, session, synchronous);
	}
	
	public void setReservationDAO(ReservationDAOIF reservationDAO) {
		this.reservationDAO = reservationDAO;
	}
}
