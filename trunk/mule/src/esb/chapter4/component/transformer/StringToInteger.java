package esb.chapter4.component.transformer;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class StringToInteger extends AbstractTransformer {

	protected Object doTransform(Object payload, String encoding) throws TransformerException {
		// We know that the payload is String
		String strPayload = (String) payload;
		try {
			return Integer.valueOf(strPayload);
		} catch(Exception e) {
			throw new TransformerException(this, e);
		}
	}
}
