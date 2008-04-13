package esb.chapter4.component.transformer;

import org.apache.log4j.Logger;

public class SimpleIntComponent {
	
	private final static Logger logger = Logger.getLogger(SimpleIntComponent.class);
	
	public String accept(Integer payload) {
		String message = "received payload " + payload;
		logger.info(message);
		return message;
	}
}