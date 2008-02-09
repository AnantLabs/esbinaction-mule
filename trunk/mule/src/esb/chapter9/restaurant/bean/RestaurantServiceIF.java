package esb.chapter9.restaurant.bean;

import esb.chapter9.restaurant.message.RestaurantInquiry;
import esb.chapter9.restaurant.message.RestaurantResponse;

public interface RestaurantServiceIF {

	public RestaurantResponse processInquiry(RestaurantInquiry inquiry);
}
