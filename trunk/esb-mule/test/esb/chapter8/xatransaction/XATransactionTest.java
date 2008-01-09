package esb.chapter8.xatransaction;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

import org.apache.activemq.ActiveMQConnection;

public class XATransactionTest extends TestCase {
	
	public void testSuccessfulXATransaction() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("xatransaction.in");
		TextMessage tMessage = session.createTextMessage();
		tMessage.setText("bad transaction");
		MessageProducer producer = session.createProducer(destination);
		System.out.println("sending transaction message");
		producer.send(tMessage);
	}

}
