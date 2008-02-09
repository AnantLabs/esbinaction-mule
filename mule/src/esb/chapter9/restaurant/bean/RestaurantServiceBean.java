package esb.chapter9.restaurant.bean;

import esb.chapter9.restaurant.message.RestaurantInquiry;
import esb.chapter9.restaurant.message.RestaurantResponse;

public class RestaurantServiceBean implements RestaurantServiceIF {
	
	private String restaurantName;
	private int maximumPersons;
	private int numberPersonsReservation;
	private String acceptedResponse;
	private String deniedResponse;
	
	public RestaurantResponse processInquiry(RestaurantInquiry inquiry) {
		RestaurantResponse response = new RestaurantResponse();
		response.setRestaurant(restaurantName);
		if((inquiry.getPersons() + numberPersonsReservation) > maximumPersons) {
			response.setResponse(deniedResponse);
			response.setTableAvailable(false);
		} else {
			response.setResponse(acceptedResponse);
			response.setTableAvailable(true);
		}
		return response;
	}
	
	public String getAcceptedResponse() {
		return acceptedResponse;
	}

	public void setAcceptedResponse(String acceptedResponse) {
		this.acceptedResponse = acceptedResponse;
	}

	public String getDeniedResponse() {
		return deniedResponse;
	}

	public void setDeniedResponse(String deniedResponse) {
		this.deniedResponse = deniedResponse;
	}

	public int getMaximumPersons() {
		return maximumPersons;
	}

	public void setMaximumPersons(int maximumPersons) {
		this.maximumPersons = maximumPersons;
	}

	public int getNumberPersonsReservation() {
		return numberPersonsReservation;
	}

	public void setNumberPersonsReservation(int numberPersonsReservation) {
		this.numberPersonsReservation = numberPersonsReservation;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

}
