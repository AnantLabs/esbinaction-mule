package esb.chapter9.restaurant.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.junit.Test;

import esb.chapter9.restaurant.message.RestaurantInquiry;
import esb.chapter9.restaurant.message.RestaurantResponse;
import esb.chapter9.restaurant.persist.ExpirationBean;

public class RestaurantTest {
	
	private List<Long> responseIDArray = new ArrayList<Long>();
	
	@Test
	public void makeReservation() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createTopic("inquiries.in");
		ObjectMessage oMessage = session.createObjectMessage();
		RestaurantInquiry inquiry = createInquiry();
		oMessage.setObject(inquiry);
		MessageProducer producer = session.createProducer(destination);
		System.out.println("sending restaurant inquiry message");
		Destination responseDestination = session.createQueue("lcdtv129.reponse");
		oMessage.setJMSReplyTo(responseDestination);
		producer.send(oMessage);
		MessageConsumer consumer = session.createConsumer(responseDestination);
		List<RestaurantResponse> restaurantList = handleRestaurantResponses(consumer);
		int userChoice = getRestaurantName(restaurantList);
		String restaurantName = restaurantList.get(userChoice).getRestaurant();
		System.out.println("you chose restaurant " + restaurantName);
		Destination confDestination = session.createQueue("reservation.confirmation");
		TextMessage confMessage = session.createTextMessage();
		confMessage.setLongProperty(ExpirationBean.RESPONSE_ID, responseIDArray.get(userChoice));
		Destination responseDestination2 = session.createQueue("lcdtv100.reponse");
		confMessage.setJMSReplyTo(responseDestination2);
		confMessage.setText(restaurantName);
		producer = session.createProducer(confDestination);
		producer.send(confMessage);
		MessageConsumer consumer2 = session.createConsumer(responseDestination2);
		Message responseMessage = consumer2.receive();
		if(responseMessage instanceof ObjectMessage) {
			ObjectMessage objResponseMessage = (ObjectMessage) responseMessage;
			System.out.println("Confirmation was succesful for restaurant " + objResponseMessage.getStringProperty("choice"));
		} else {
			TextMessage txtResponseMessage = (TextMessage) responseMessage;
			System.out.println("Confirmation was not succesful for restaurant " + txtResponseMessage.getText());
		}
	}
	
	private RestaurantInquiry createInquiry() throws Exception {
		RestaurantInquiry inquiry = new RestaurantInquiry();
		System.out.println("Welcome to our restaurant table reservation application");
		System.out.println("Please provide your guest name?");
		BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));
		String guestName = infile.readLine();
		inquiry.setGuestName(guestName);
		System.out.println("Please provide your room number?");
		infile = new BufferedReader(new InputStreamReader(System.in));
		String roomNumber = infile.readLine();
		inquiry.setRoomNumber(Integer.valueOf(roomNumber));
		System.out.println("How many persons can we include in your reservation?");
		infile = new BufferedReader(new InputStreamReader(System.in));
		String personsValue = infile.readLine();
		inquiry.setPersons(Integer.valueOf(personsValue));
		System.out.println("And which time can we set for your reservation? (hh:mm)");
		infile = new BufferedReader(new InputStreamReader(System.in));
		String timeValue = infile.readLine();
		Calendar reservationCal = new GregorianCalendar();
		reservationCal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeValue.substring(0,2)));
		reservationCal.set(Calendar.MINUTE, Integer.valueOf(timeValue.substring(3)));
		inquiry.setReservationTime(reservationCal.getTime());
		return inquiry;
	}
	
	private List<RestaurantResponse> handleRestaurantResponses(MessageConsumer consumer) throws Exception {
		List<RestaurantResponse> restaurantList = new ArrayList<RestaurantResponse>();
		System.out.println("receiving restaurant response message");
		ObjectMessage responseMessage = (ObjectMessage) consumer.receive();
		RestaurantResponse restaurantResponse1 = (RestaurantResponse) responseMessage.getObject();
		if(restaurantResponse1.isTableAvailable()) {
			responseIDArray.add(responseMessage.getLongProperty(ExpirationBean.RESPONSE_ID));
			restaurantList.add(restaurantResponse1);
		}
		responseMessage = (ObjectMessage) consumer.receive();
		RestaurantResponse restaurantResponse2 = (RestaurantResponse) responseMessage.getObject();
		if(restaurantResponse2.isTableAvailable()) {
			responseIDArray.add(responseMessage.getLongProperty(ExpirationBean.RESPONSE_ID));
			restaurantList.add(restaurantResponse2);
		}
		responseMessage = (ObjectMessage) consumer.receive();
		RestaurantResponse restaurantResponse3 = (RestaurantResponse) responseMessage.getObject();
		if(restaurantResponse3.isTableAvailable()) {
			responseIDArray.add(responseMessage.getLongProperty(ExpirationBean.RESPONSE_ID));
			restaurantList.add(restaurantResponse3);
		}
		return restaurantList;
	}
	
	private int getRestaurantName(List<RestaurantResponse> restaurantList) throws Exception {
		boolean userresponse = true;
		while(userresponse) {
			for(int i = 0; i < restaurantList.size(); i++) {
				System.out.println(i + 1 + ". " + restaurantList.get(i).getRestaurant() + 
						" with response " + restaurantList.get(i).getResponse());
			}
			System.out.println("Please provide your choice to confirm");
			BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));
			String line = infile.readLine();
			if(line.length() != 1 || !Character.isDigit(line.charAt(0))) {
				System.out.println("Please provide a value of 1 to " + restaurantList.size());
			} else {
				int intResponse = Integer.valueOf(line);
				if(intResponse >= 1 && intResponse <= restaurantList.size()) {
					return intResponse - 1;
				} else {
					System.out.println("Please provide a value of 1 to " + restaurantList.size());
				}
			}
		}
		// we should never come here
		return -1;
	}
	
}
