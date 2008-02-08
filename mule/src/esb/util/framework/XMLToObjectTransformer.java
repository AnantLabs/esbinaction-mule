package esb.util.framework;

import org.jibx.runtime.JiBXException;

import esb.util.framework.JiBXUtil;

public class XMLToObjectTransformer extends AbstractJibxTransformer {


	private static final long serialVersionUID = 607947068516188677L;
	private String targetClassName;

	/**
	 * The input is a Stirng the output is an Inquiry
	 */
	@Override
	protected Object doTransform(Object src) throws JiBXException {
			try {
				return JiBXUtil.unmarshall((String)src,Class.forName(targetClassName));
			} catch (ClassNotFoundException e) {
				throw new JiBXException("Class not found: " +e.getMessage());
			}
	}
	
	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}
}
