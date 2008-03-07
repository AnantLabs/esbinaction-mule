package esb.chapter8.security.authorization.mule;

import org.acegisecurity.AccessDeniedException;
import org.mule.RequestContext;
import org.mule.api.security.UnauthorisedException;
import org.mule.config.i18n.CoreMessages;
import org.mule.service.DefaultServiceExceptionStrategy;

public class AuthorizationExceptionStrategy extends DefaultServiceExceptionStrategy {
	protected void defaultHandler(Throwable t) {
		System.out.println("error******** " + t.getCause());
	  	if(t.getCause() instanceof AccessDeniedException) {
	  		super.defaultHandler(new UnauthorisedException(CoreMessages.authDeniedOnEndpoint(
	  				RequestContext.getEventContext().getEndpointURI())));
	  	} else {
	  		super.defaultHandler(t);
	  	}
	}

}
