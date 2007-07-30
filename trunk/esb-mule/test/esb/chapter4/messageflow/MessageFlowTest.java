package esb.chapter4.messageflow;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import junit.framework.TestCase;

import org.apache.activemq.ActiveMQConnection;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MessageFlowTest extends TestCase {
	
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
	    Destination destination = session.createQueue("amazon.input");
		TextMessage isbnMessage = session.createTextMessage();
		isbnMessage.setText("1010101010");
		MessageProducer producer = session.createProducer(destination);
		producer.send(isbnMessage);
		Thread.sleep(5000);
	}

}
