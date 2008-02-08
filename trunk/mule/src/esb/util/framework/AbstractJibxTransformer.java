package esb.util.framework;

import org.jibx.runtime.JiBXException;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public abstract class AbstractJibxTransformer extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String encoding)
			throws TransformerException {
		try {
			return doTransform(src);	
		} catch (JiBXException e) {
			throw new TransformerException(this,e);
		}
	}

	abstract protected Object doTransform(Object src) throws JiBXException;

}
