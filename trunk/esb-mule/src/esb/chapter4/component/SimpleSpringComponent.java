package esb.chapter4.component;

import org.mule.umo.UMOEventContext;
import org.mule.umo.lifecycle.Callable;

public class SimpleSpringComponent implements Callable {
	
	private String message;
	
	public String accept(String payload) {
		return message + payload;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object onCall(UMOEventContext context) throws Exception {
		String payload = context.getMessage().getPayloadAsString();
		return message + payload;
	}
}
