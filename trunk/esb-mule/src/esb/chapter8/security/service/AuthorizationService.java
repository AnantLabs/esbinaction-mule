package esb.chapter8.security.service;

import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.mule.umo.UMOEventContext;
import org.mule.umo.lifecycle.Callable;
import org.mule.umo.security.UMOAuthentication;

public class AuthorizationService implements Callable {

	public Object onCall(UMOEventContext context) throws Exception {
		UMOAuthentication auth = 
			context.getSession().getSecurityContext().getAuthentication(); 
		LdapUserDetails userDetails = (LdapUserDetails) auth.getPrincipal();
		return "You have been authorized as " + userDetails.getUsername();
	}

}
