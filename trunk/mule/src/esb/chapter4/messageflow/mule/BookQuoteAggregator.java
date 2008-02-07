package esb.chapter4.messageflow.mule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.routing.AggregationException;
import org.mule.routing.inbound.AbstractEventAggregator;
import org.mule.routing.inbound.EventGroup;

import esb.chapter4.messageflow.domain.BookQuote;

public class BookQuoteAggregator extends AbstractEventAggregator {
	
	private static final Logger logger = Logger.getLogger(BookQuoteAggregator.class);
	
	protected Object getEventGroupIdForEvent(MuleEvent event) {
		MuleMessage message = event.getMessage();
		BookQuote quote = (BookQuote) message.getPayload();
		return quote.getIsbn();
	}

	protected boolean shouldAggregateEvents(EventGroup eventGroup) {
		Iterator itEvent = eventGroup.iterator();
		boolean isAmazonPresent = false;
		boolean isBarnesPresent = false;
		while(itEvent.hasNext()) {
			MuleEvent event = (MuleEvent) itEvent.next();
			BookQuote quote = (BookQuote) event.getMessage().getPayload();
			String companyName = quote.getCompanyName();
			logger.info("companyName present " + companyName);
			if("Amazon".equalsIgnoreCase(companyName)) {
				isAmazonPresent = true;
			} else if("BarnesAndNoble".equalsIgnoreCase(companyName)) {
				isBarnesPresent = true;
			}
		}
		return isAmazonPresent && isBarnesPresent;
	}
	
	protected MuleMessage aggregateEvents(EventGroup eventGroup) throws AggregationException {
		Iterator itEvent = eventGroup.iterator();
		Collection<BookQuote> quoteList = new ArrayList<BookQuote>();
		while(itEvent.hasNext()) {
			MuleEvent event = (MuleEvent) itEvent.next();
			BookQuote quote = (BookQuote) event.getMessage().getPayload();
			quoteList.add(quote);
		}
		return new DefaultMuleMessage(quoteList);
	}

}
