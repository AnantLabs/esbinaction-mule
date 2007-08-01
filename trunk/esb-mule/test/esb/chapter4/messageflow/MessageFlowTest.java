package esb.chapter4.messageflow;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import esb.chapter4.messageflow.domain.BookQuote;

public class MessageFlowTest extends TestCase {
	
	private static final Logger logger = Logger.getLogger(MessageFlowTest.class);
	FileSystemXmlApplicationContext appContext;
	
	protected void setUp() throws Exception {
		appContext = new FileSystemXmlApplicationContext("file:resources/chapter4/messageflow/spring/messageflow-spring.xml");
	}
	
	protected void tearDown() throws Exception {
		appContext.destroy();
	}
	
	public void testMessageFlow() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("booksearch.input");
		TextMessage isbnMessage = session.createTextMessage();
		isbnMessage.setText("9999999999");
		MessageProducer producer = session.createProducer(destination);
		producer.send(isbnMessage);
		Destination responseDestination = session.createQueue("booksearch.output");
		MessageConsumer consumer = session.createConsumer(responseDestination);
		ObjectMessage quoteObject = (ObjectMessage) consumer.receive();
		BookQuote bookQuote = (BookQuote) quoteObject.getObject();
		logger.info("received cheapest quote from " + bookQuote.getCompanyName() + ", " + bookQuote.getPrice());
		
	}

}
