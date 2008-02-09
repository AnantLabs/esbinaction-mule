package esb.chapter8.security.authentication.transformer;

import org.mule.api.MuleMessage;
import org.mule.api.config.MuleProperties;
import org.mule.api.transformer.TransformerException;
import org.mule.security.MuleCredentials;
import org.mule.transformer.AbstractMessageAwareTransformer;

public class SimpleAuthenticationTransformer extends AbstractMessageAwareTransformer {

	public Object transform(MuleMessage message, String encoding) throws TransformerException {
		String userCredentials = (String) message.getPayload();
		int splitChar = userCredentials.indexOf(";");
		if(splitChar == -1) {
			throw new TransformerException(this, new NullPointerException("expected format of username;password"));
		}
		String username = userCredentials.substring(0, splitChar);
		String password = userCredentials.substring(splitChar + 1);
		String muleCredentials = MuleCredentials.createHeader(username, password.toCharArray());
		message.setProperty(MuleProperties.MULE_USER_PROPERTY, muleCredentials);
		return userCredentials;
		
	}

}
