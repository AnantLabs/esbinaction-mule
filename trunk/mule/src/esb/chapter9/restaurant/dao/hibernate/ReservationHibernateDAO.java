package esb.chapter9.restaurant.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import esb.chapter9.restaurant.dao.ReservationDAOIF;
import esb.chapter9.restaurant.model.Reservation;
import esb.chapter9.restaurant.model.Restaurant;

public class ReservationHibernateDAO extends HibernateDaoSupport implements ReservationDAOIF {
	
	public Reservation getReservation(long reservationID) {
		return (Reservation) getHibernateTemplate().get(Reservation.class, reservationID);
	}
	
	public Reservation saveReservation(Reservation reservation) {
		String restaurantName = reservation.getRestaurant().getName();
		reservation.setRestaurant(getRestaurant(restaurantName));
		System.out.println("restaurant " + reservation.getRestaurant().getName() + ", id=" + reservation.getRestaurant().getRestaurantID());
		Long reservationId = (Long) getHibernateTemplate().save(reservation);
		return getReservation(reservationId);
	}
	
	public Restaurant getRestaurant(String name) {
		List restaurantList = getHibernateTemplate().find("from Restaurant where name = ?", new Object[]{name});
		if(restaurantList != null)
			return (Restaurant) restaurantList.get(0);
		else
			return null;
	}

}
