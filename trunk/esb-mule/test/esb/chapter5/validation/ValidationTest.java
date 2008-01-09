package esb.chapter5.validation;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

import org.apache.activemq.ActiveMQConnection;

public class ValidationTest extends TestCase {
	
	public void testValidation() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("validation.in");
		TextMessage isbnMessage = session.createTextMessage();
		isbnMessage.setText(getValidOrderMessage());
		MessageProducer producer = session.createProducer(destination);
		System.out.println("sending order");
		producer.send(isbnMessage);
		Destination responseDestination = session.createQueue("validation.out");
		MessageConsumer consumer = session.createConsumer(responseDestination);
		System.out.println("receiving order");
		TextMessage returnMessage = (TextMessage) consumer.receive();
		System.out.println("received order " + returnMessage.getText());
	}
	
	public void testInvalidMessage() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("validation.in");
		TextMessage isbnMessage = session.createTextMessage();
		isbnMessage.setText(getInvalidOrderMessage());
		MessageProducer producer = session.createProducer(destination);
		System.out.println("sending order");
		producer.send(isbnMessage);
		Destination responseDestination = session.createQueue("validation.error");
		MessageConsumer consumer = session.createConsumer(responseDestination);
		System.out.println("receiving failed order");
		TextMessage returnMessage = (TextMessage) consumer.receive();
		System.out.println("received failed order " + returnMessage.getText());
	}
	
	private String getValidOrderMessage() {
		return "<order xmlns=\"http://esbinaction.com/order\">" + 
			"<clientname>John Doe</clientname>" + 
			"<productname>Book</productname>" + 
			"<quantity>1</quantity>" + 
			"<orderdate>2007-11-11</orderdate>" +
			"</order>";
	}
	
	private String getInvalidOrderMessage() {
		return "<order xmlns=\"http://esbinaction.com/order\">" + 
			"<clientname2>John Doe</clientname2>" + 
			"<productname>Book</productname>" + 
			"<quantity>1</quantity>" + 
			"<orderdate>2007-11-11</orderdate>" +
			"</order>";
	}
	
}
