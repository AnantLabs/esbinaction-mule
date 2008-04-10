package esb.chapter9.restaurant.message;

import java.io.Serializable;

public class RestaurantResponse implements Serializable {

	private static final long serialVersionUID = -7522343661961986789L;
	private String restaurant;
	private String response;
	private boolean tableAvailable;
	private RestaurantInquiry inquiry;
	
	public RestaurantInquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(RestaurantInquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isTableAvailable() {
		return tableAvailable;
	}

	public void setTableAvailable(boolean tableAvailable) {
		this.tableAvailable = tableAvailable;
	}
}
