package esb.chapter9.restaurant.model;

import java.io.Serializable;

public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = 1067952983045716480L;
	private Long restaurantID;
	private String name;
	private Long timeout;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(Long restaurantID) {
		this.restaurantID = restaurantID;
	}
	public Long getTimeout() {
		return timeout;
	}
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}
}
