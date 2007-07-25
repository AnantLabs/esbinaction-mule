package esb.chapter4.component;

import org.apache.log4j.Logger;

public class SimpleComponent {
	
	private final static Logger logger = Logger.getLogger(SimpleComponent.class);
	
	public void accept(String payload) {
		logger.info("received payload " + payload);
	}
}
