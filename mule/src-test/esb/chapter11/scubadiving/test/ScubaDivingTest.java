package esb.chapter11.scubadiving.test;

import java.util.GregorianCalendar;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.junit.Test;

import esb.chapter11.scubadiving.model.DivingInterest;
import esb.chapter11.scubadiving.model.ScubaDivingBooking;
import esb.chapter11.scubadiving.model.ScubaDivingRequest;

public class ScubaDivingTest {
	
	@Test
	public void goScubaDive() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("process.start");
		ScubaDivingRequest request = new ScubaDivingRequest();
		request.setGuestName("Mr Diver");
		request.setInterest(DivingInterest.Sharks);
		request.setLunchIncluded(true);
		request.setNumberOfDivers(10);
		request.setRoomNumber(600);
		request.setRequestDate(new GregorianCalendar().getTime());
		ObjectMessage objMessage = session.createObjectMessage();
		objMessage.setObject(request);
		MessageProducer producer = session.createProducer(destination);
		producer.send(objMessage);
		Destination responseDestination = session.createQueue("process.end");
		MessageConsumer consumer = session.createConsumer(responseDestination);
		ObjectMessage objectMsg = (ObjectMessage) consumer.receive(2000);
		ScubaDivingBooking booking = (ScubaDivingBooking) objectMsg.getObject();
		System.out.println("school name " + booking.getSchoolName() + ", price " + booking.getTotalPrice());
	}
}
