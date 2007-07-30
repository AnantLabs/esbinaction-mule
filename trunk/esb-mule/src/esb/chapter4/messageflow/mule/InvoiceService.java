package esb.chapter4.messageflow.mule;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;

public class InvoiceService implements MessageListener {
	
	private static final Logger logger = Logger.getLogger(BookQuoteAggregator.class);
	
	public void onMessage(Message orderMessage) {
		logger.info("received order " + orderMessage);
		if(!(orderMessage instanceof TextMessage)) {
			logger.error("expected order message of type TextMessage, ignoring this message");
			return;
		}
		try {
			String orderString = ((TextMessage) orderMessage).getText();
			logger.info("order contents " + orderString);
			ActiveMQConnection connection = ActiveMQConnection.makeConnection("tcp://localhost:61616");
		    connection.start();
		    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    Destination destination = session.createQueue("invoice.output");
		    MessageProducer producer = session.createProducer(destination);
			producer.send(orderMessage);
		} catch(Exception e) {
			logger.error("exception while processing message", e);
		}
	}

}
