package esb.chapter3;

import java.io.StringReader;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IUnmarshallingContext;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class XMLToObjectTransformer extends AbstractTransformer {

	private String targetClassName;

	protected Object doTransform(Object data, String encoding) throws TransformerException {
		try {
			IUnmarshallingContext ctx = BindingDirectory.getFactory(
					Class.forName(targetClassName))
					.createUnmarshallingContext();
			return ctx.unmarshalDocument(new StringReader((String) data));
		} catch (Exception e) {
			throw new TransformerException(this, e);
		}
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}
}
