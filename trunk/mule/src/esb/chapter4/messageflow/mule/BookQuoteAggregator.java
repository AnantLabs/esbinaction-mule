package esb.chapter4.messageflow.mule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.routing.MessageInfoMapping;
import org.mule.routing.EventCorrelatorCallback;
import org.mule.routing.MuleMessageInfoMapping;
import org.mule.routing.inbound.AbstractEventAggregator;
import org.mule.routing.inbound.EventGroup;

import esb.chapter4.messageflow.domain.BookQuote;

public class BookQuoteAggregator extends AbstractEventAggregator {
	
	private static final Logger logger = Logger.getLogger(BookQuoteAggregator.class);
	
	@Override
	protected EventCorrelatorCallback getCorrelatorCallback() {
		return new EventCorrelatorCallback()
		{
			public MuleMessage aggregateEvents(EventGroup events) {
				Iterator itEvent = events.iterator();
				Collection<BookQuote> quoteList = new ArrayList<BookQuote>();
				while(itEvent.hasNext()) {
					MuleEvent event = (MuleEvent) itEvent.next();
					BookQuote quote = (BookQuote) event.getMessage().getPayload();
					quoteList.add(quote);
				}
				return new DefaultMuleMessage(quoteList);
			}

			public EventGroup createEventGroup(MuleEvent event, Object correlationID) {
				// create event group with correlation id ISBN and expected size 2
				return new EventGroup(correlationID, 2);
			}

			public boolean shouldAggregateEvents(EventGroup events) {
				Iterator itEvent = events.iterator();
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
		};
	}
	
	@Override
	public MessageInfoMapping getMessageInfoMapping() {
		return new MuleMessageInfoMapping()
		{
			public String getCorrelationId(MuleMessage message) {
				BookQuote quote = (BookQuote) message.getPayload();
				return quote.getIsbn();
			}
		};
	}
}
