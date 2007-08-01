package esb.chapter4.messageflow.mule;

import org.apache.log4j.Logger;
import org.mule.umo.UMOEventContext;
import org.mule.umo.UMOMessage;
import org.mule.umo.lifecycle.Callable;

public class MessageLogger implements Callable {
	
	private static final Logger logger = Logger.getLogger(MessageLogger.class);

	public Object onCall(UMOEventContext context) throws Exception {
		UMOMessage incomingMessage = context.getMessage();
		logger.info("payload " + incomingMessage.getPayload());
		return incomingMessage;
	}

}
