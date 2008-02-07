package esb.chapter4.messageflow.mule;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class MessageLogger implements Callable {
	
	private static final Logger logger = Logger.getLogger(MessageLogger.class);

	public Object onCall(MuleEventContext context) throws Exception {
		MuleMessage incomingMessage = context.getMessage();
		logger.info("payload " + incomingMessage.getPayload());
		return incomingMessage;
	}

}
