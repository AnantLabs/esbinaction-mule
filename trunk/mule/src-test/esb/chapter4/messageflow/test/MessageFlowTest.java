package esb.chapter4.messageflow.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import esb.chapter4.messageflow.domain.BookQuote;

public class MessageFlowTest {
	
	private static final Logger logger = Logger.getLogger(MessageFlowTest.class);
	private ActiveMQConnection connection;
	private Session session;
	private MessageProducer producer;
	private MessageConsumer consumer;
	
	@Before
	public void initialize() throws Exception {
		connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("booksearch.input");
	    producer = session.createProducer(destination);
	    Destination responseDestination = session.createQueue("booksearch.output");
		consumer = session.createConsumer(responseDestination);
	}
	
	@After
	public void tearDown() throws Exception {
		connection.stop();
	}
	
	@Test
	public void barnesMessageFlow() throws Exception {
		TextMessage isbnMessage = session.createTextMessage();
		isbnMessage.setText("9999999999");
		producer.send(isbnMessage);
		ObjectMessage quoteObject = (ObjectMessage) consumer.receive(2000);
		assertNotNull(quoteObject);
		BookQuote bookQuote = (BookQuote) quoteObject.getObject();
		assertNotNull(bookQuote);
		assertEquals("BarnesAndNoble", bookQuote.getCompanyName());
		logger.info("received cheapest quote from " + bookQuote.getCompanyName() + ", " + bookQuote.getPrice());
	}
	
	@Test
	public void amazonMessageFlow() throws Exception {
		TextMessage isbnMessage = session.createTextMessage();
		isbnMessage.setText("1010101010");
		producer.send(isbnMessage);
		ObjectMessage quoteObject = (ObjectMessage) consumer.receive(2000);
		assertNotNull(quoteObject);
		BookQuote bookQuote = (BookQuote) quoteObject.getObject();
		assertNotNull(bookQuote);
		assertEquals("Amazon", bookQuote.getCompanyName());
		logger.info("received cheapest quote from " + bookQuote.getCompanyName() + ", " + bookQuote.getPrice());
	}

}
