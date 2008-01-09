package esb.chapter8.security;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

import org.apache.activemq.ActiveMQConnection;

public class SSLTest extends TestCase {
	
	public void testSSL() throws Exception {
		System.setProperty("javax.net.ssl.keyStore", "C:/book/distributions/apache-activemq-4.1.1/conf/client.ks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore", "C:/book/distributions/apache-activemq-4.1.1/conf/client.ts");
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("ssl://localhost:61617");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("ssl.in");
		TextMessage isbnMessage = session.createTextMessage();
		isbnMessage.setText(getHelloMessage());
		MessageProducer producer = session.createProducer(destination);
		System.out.println("sending hello message");
		producer.send(isbnMessage);
		Destination responseDestination = session.createQueue("ssl.out");
		MessageConsumer consumer = session.createConsumer(responseDestination);
		System.out.println("receiving hello message");
		TextMessage returnMessage = (TextMessage) consumer.receive();
		System.out.println("received hello " + returnMessage.getText());
	}
	
	private String getHelloMessage() {
		return "<hello>" + 
			"secure" + 
			"</hello>";
	}
}
