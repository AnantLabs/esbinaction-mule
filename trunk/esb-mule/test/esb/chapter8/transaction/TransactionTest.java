package esb.chapter8.transaction;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

import org.apache.activemq.ActiveMQConnection;

public class TransactionTest extends TestCase {
	
	/*private final static String MULE_CONFIG = 
		"file:resources/chapter8/transaction/mule/transaction-config.xml";

	private MuleServer server;

	protected void setUp() throws Exception {
		server = new MuleServer(MULE_CONFIG);
		server.start(false, false);
	}

	protected void tearDown() throws Exception {
		server.shutdown();
	}*/
	
	public void testSuccessfulTransaction() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("transaction.in");
		TextMessage tMessage = session.createTextMessage();
		tMessage.setText("Start transaction");
		MessageProducer producer = session.createProducer(destination);
		System.out.println("sending transaction message");
		producer.send(tMessage);
		Destination responseDestination = session.createQueue("transaction.out");
		MessageConsumer consumer = session.createConsumer(responseDestination);
		System.out.println("receiving transaction message");
		TextMessage returnMessage = (TextMessage) consumer.receive();
		System.out.println("received transaction message " + returnMessage.getText());
	}
	
	public void testFailedTransaction() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("transaction.in");
		TextMessage tMessage = session.createTextMessage();
		tMessage.setText("bad transaction");
		MessageProducer producer = session.createProducer(destination);
		System.out.println("sending bad transaction message");
		producer.send(tMessage);
		Destination responseDestination = session.createQueue("DLQ.transaction.in");
		MessageConsumer consumer = session.createConsumer(responseDestination);
		System.out.println("receiving bad transaction message from DLQ");
		TextMessage returnMessage = (TextMessage) consumer.receive();
		System.out.println("received bad transaction message from DLQ" + returnMessage.getText());
	}

}
