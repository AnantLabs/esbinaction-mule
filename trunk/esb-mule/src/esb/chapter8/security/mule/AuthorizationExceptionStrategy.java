package esb.chapter8.security.mule;

import org.acegisecurity.AccessDeniedException;
import org.mule.config.i18n.CoreMessages;
import org.mule.impl.DefaultComponentExceptionStrategy;
import org.mule.impl.RequestContext;
import org.mule.umo.security.UnauthorisedException;

public class AuthorizationExceptionStrategy extends DefaultComponentExceptionStrategy {
	protected void defaultHandler(Throwable t) {
	  	if(t.getCause() instanceof AccessDeniedException) {
	  		super.defaultHandler(new UnauthorisedException(CoreMessages.authDeniedOnEndpoint(
	  				RequestContext.getEventContext().getEndpointURI())));
	  	} else {
	  		super.defaultHandler(t);
	  	}
	}

}
