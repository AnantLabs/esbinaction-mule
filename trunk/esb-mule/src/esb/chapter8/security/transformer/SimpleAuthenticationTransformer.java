package esb.chapter8.security.transformer;

import org.mule.config.MuleProperties;
import org.mule.impl.security.MuleCredentials;
import org.mule.transformers.AbstractMessageAwareTransformer;
import org.mule.umo.UMOMessage;
import org.mule.umo.transformer.TransformerException;

public class SimpleAuthenticationTransformer extends AbstractMessageAwareTransformer {

	public Object transform(UMOMessage message, String encoding) throws TransformerException {
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
