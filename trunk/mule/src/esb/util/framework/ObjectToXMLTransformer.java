package esb.util.framework;

import org.jibx.runtime.JiBXException;

import esb.util.framework.JiBXUtil;

public class ObjectToXMLTransformer extends AbstractJibxTransformer {

	private static final long serialVersionUID = 4307312523309319537L;

	@Override
	protected Object doTransform(Object src) throws JiBXException {
		return JiBXUtil.marshall(src);
	}

}
