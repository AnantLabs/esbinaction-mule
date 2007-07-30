package esb.chapter4.messageflow.service;

import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;

public class AmazonBookStore implements MessageListener {
	
	private static final Logger logger = Logger.getLogger(AmazonBookStore.class);
	private Map<String, Float> bookMap;
	private String connectionURL;
	
	public void onMessage(Message isbnMessage) {
		if(!(isbnMessage instanceof TextMessage)) {
			logger.error("expected order message of type TextMessage, ignoring this message");
			return;
		}
		try {
			String isbn = ((TextMessage) isbnMessage).getText();
			Float priceQuote = null;
			if(bookMap.containsKey(isbn)) {
				priceQuote = bookMap.get(isbn);
			}
			ActiveMQConnection connection = ActiveMQConnection.makeConnection(connectionURL);
		    connection.start();
		    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    MessageProducer producer = session.createProducer(isbnMessage.getJMSReplyTo());
		    TextMessage quoteMessage = session.createTextMessage();
		    quoteMessage.setText(priceQuote.toString());
			producer.send(quoteMessage);
		} catch(Exception e) {
			logger.error("exception while processing message", e);
		}
	}

	public Map<String, Float> getBookMap() {
		return bookMap;
	}

	public void setBookMap(Map<String, Float> bookMap) {
		this.bookMap = bookMap;
	}

	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}
}
