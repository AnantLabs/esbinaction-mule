package esb.chapter4.component;

import org.apache.log4j.Logger;

public class SimpleIntComponent {
	
	private final static Logger logger = Logger.getLogger(SimpleIntComponent.class);
	
	public void accept(Integer payload) {
		logger.info("received payload " + payload);
	}
}
