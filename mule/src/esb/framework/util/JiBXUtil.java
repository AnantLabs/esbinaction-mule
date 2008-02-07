package esb.framework.util;

import java.io.StringReader;
import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class JiBXUtil {
	
	public static String marshall(Object src) throws JiBXException {
		StringWriter outWriter = new StringWriter();
		IMarshallingContext ctx = BindingDirectory.getFactory(src.getClass()).createMarshallingContext();
		ctx.marshalDocument(src, "UTF-8", null, outWriter);
		return outWriter.toString();
	}

	public static Object unmarshall(String data, Class<?> clazz) throws JiBXException {
		IUnmarshallingContext ctx = BindingDirectory.getFactory(clazz).createUnmarshallingContext();
		return ctx.unmarshalDocument(new StringReader(data));
	}

}
