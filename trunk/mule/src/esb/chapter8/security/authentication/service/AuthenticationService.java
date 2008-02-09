package esb.chapter8.security.authentication.service;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;


public class AuthenticationService implements Callable {
	
	private static final Logger logger = Logger.getLogger(AuthenticationService.class);
	
	private boolean authenticated;
	
	public Object onCall(MuleEventContext context) throws Exception {
		logger.info("AuthenticationService has been called with " + authenticated);
		logger.info("received payload " + context.getMessage().getPayload().getClass().getName());
		return "Has the user been authenticated? " + authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
