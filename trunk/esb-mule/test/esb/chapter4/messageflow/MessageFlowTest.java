package esb.chapter4.messageflow;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import junit.framework.TestCase;

import org.apache.activemq.ActiveMQConnection;

import esb.chapter4.service.InvoiceService;

public class MessageFlowTest extends TestCase {
	
	public void testMessageFlow() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination destination = session.createQueue("callcenter.output");
	    Destination replyDestination = session.createQueue("callcenter.input");
		ObjectMessage testMessage = session.createObjectMessage();
		Order order = new Order();
		order.setOrderID(new Long(10390));
		order.setClientNumber("0340535DSK");
		order.setOrderDescription("description");
		order.setAmount(new Float(24.5f));
		testMessage.setObject(order);
		MessageProducer producer = session.createProducer(destination);
		createListener();
		producer.send(testMessage);
		MessageConsumer replyConsumer = session.createConsumer(replyDestination);
		Message invoiceMessage = replyConsumer.receive();
		System.out.println("received invoice " + invoiceMessage.getClass().getName());
	}
	
	private void createListener() throws Exception {
		ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
	    connection.start();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination replyDestinationInvoice = session.createQueue("invoice.input");
		MessageConsumer consumer = session.createConsumer(replyDestinationInvoice);
		consumer.setMessageListener(new InvoiceService());
	}

}
