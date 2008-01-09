package esb.chapter8.security.service;

import org.apache.log4j.Logger;
import org.mule.umo.UMOEventContext;
import org.mule.umo.lifecycle.Callable;


public class AuthenticationService implements Callable {
	
	private static final Logger logger = Logger.getLogger(AuthenticationService.class);
	
	private boolean authenticated;
	
	public Object onCall(UMOEventContext context) throws Exception {
		logger.info("AuthenticationService has been called with " + authenticated);
		logger.info("received payload " + context.getMessage().getPayload().getClass().getName());
		return "Has the user been authenticated? " + authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
