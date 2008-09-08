package esb.chapter10;

import esb.chapter9.restaurant.message.RestaurantConfirmation;

public class DetourMessageProcessor {

	public void processConfirmationMessage(String conf) {
		System.out.println("Welcome to the detour pattern:");
		System.out.println("We could now do anything with the chosen message");
	}
}
