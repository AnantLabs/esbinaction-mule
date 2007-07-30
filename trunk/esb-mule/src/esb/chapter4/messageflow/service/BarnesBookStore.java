package esb.chapter4.messageflow.service;

import java.io.StringReader;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class BarnesBookStore implements MessageListener {
	
	private static final Logger logger = Logger.getLogger(BarnesBookStore.class);
	private Map<String, Float> bookMap;
	private String connectionURL;
	
	public BarnesBookStore(String connectionURL, String queueName, Map<String, Float> bookMap) {
		try {
			ActiveMQConnection connection = ActiveMQConnection.makeConnection(connectionURL);
		    connection.start();
		    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    Destination inputQueue = session.createQueue(queueName);
			MessageConsumer consumer = session.createConsumer(inputQueue);
			consumer.setMessageListener(this);
			this.connectionURL = connectionURL;
			this.bookMap = bookMap;
		} catch(Exception e) {
			logger.error("error while creating JMS connection", e);
		}
	}
	
	public void onMessage(Message isbnMessage) {
		if(!(isbnMessage instanceof TextMessage)) {
			logger.error("expected order message of type TextMessage, ignoring this message");
			return;
		}
		try {
			String xmlMessage = ((TextMessage) isbnMessage).getText();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.parse(new InputSource(new StringReader(xmlMessage)));
	        String isbn = document.getDocumentElement().getTextContent();
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
}
