package esb.chapter4.messageflow.mule;

import java.util.Collection;

import org.apache.log4j.Logger;

import esb.chapter4.messageflow.domain.BookQuote;

public class CheapestPriceCalculator {
	
	private static final Logger logger = Logger.getLogger(CheapestPriceCalculator.class);

	public BookQuote calculate(Collection<BookQuote> quoteList) {
		BookQuote cheapestQuote = null;
		for(BookQuote quote : quoteList) {
			if(cheapestQuote == null || quote.getPrice() <= cheapestQuote.getPrice()) {
				logger.info("cheapest quote for now " + quote.getCompanyName());
				cheapestQuote = quote;
			}
		}
		return cheapestQuote;
	}
}
