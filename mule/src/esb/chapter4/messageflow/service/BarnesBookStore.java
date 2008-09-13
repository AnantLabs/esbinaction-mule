package esb.chapter4.messageflow.service;

import java.io.StringReader;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.Message;
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

import esb.chapter4.messageflow.domain.BookQuote;
import esb.util.framework.JiBXUtil;

public class BarnesBookStore implements MessageListener {
	
	private static final Logger logger = Logger.getLogger(BarnesBookStore.class);
	private Map<String, Float> bookMap;
	private String connectionURL;
	
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
			} else {
				logger.error("isbn " + isbn + " not found, not returning a response message");
			}
			ActiveMQConnection connection = ActiveMQConnection.makeConnection(connectionURL);
		    connection.start();
		    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    Destination output = session.createQueue("barnes.output");
		    MessageProducer producer = session.createProducer(output);
		    TextMessage quoteMessage = session.createTextMessage();
		    BookQuote bookQuote = new BookQuote();
		    bookQuote.setIsbn(isbn);
		    bookQuote.setPrice(priceQuote);
		    bookQuote.setCompanyName("BarnesAndNoble");
		    quoteMessage.setText(JiBXUtil.marshall(bookQuote));
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
