package esb.chapter8.security.authorization.service;

import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.api.security.Authentication;

public class AuthorizationService implements Callable {

	public Object onCall(MuleEventContext context) throws Exception {
		Authentication auth = 
			context.getSession().getSecurityContext().getAuthentication(); 
		LdapUserDetails userDetails = (LdapUserDetails) auth.getPrincipal();
		return "You have been authorized as " + userDetails.getUsername();
	}

}
