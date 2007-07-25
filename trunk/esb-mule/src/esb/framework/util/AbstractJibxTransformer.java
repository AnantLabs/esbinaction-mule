package esb.framework.util;

import org.jibx.runtime.JiBXException;
import org.mule.transformers.AbstractTransformer;
import org.mule.umo.transformer.TransformerException;

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
