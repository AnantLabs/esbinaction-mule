package esb.chapter9.restaurant.dao;

import esb.chapter9.restaurant.model.Reservation;

public interface ReservationDAOIF {
	
	public Reservation getReservation(long reservationID);
	
	public Reservation saveReservation(Reservation reservation);

}
