package esb.chapter3;

import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class ObjectToXMLTransformer extends AbstractTransformer {

    
	@Override
	protected Object doTransform(Object src, String encoding)
			throws TransformerException {
		try {
			StringWriter outWriter = new StringWriter();
			IMarshallingContext ctx = BindingDirectory.getFactory(
					src.getClass()).createMarshallingContext();
			ctx.marshalDocument(src, "UTF-8", null, outWriter);
			return outWriter.toString();
		} catch (JiBXException e) {
			throw new TransformerException(this, e);
		}
	}
}
