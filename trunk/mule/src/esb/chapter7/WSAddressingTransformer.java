package esb.chapter7;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.message.MessageImpl;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageAwareTransformer;

/**
 * Transformer which reads WSA headers from a soap body using the CXF interceptors
 * and adds these headers to the mule message. These headers can now easily be used
 * to apply content based routing.
 * 
 * @author nl24167
 */
public class WSAddressingTransformer extends AbstractMessageAwareTransformer{

    /**
     * Get the addressing headers from the byte[] and add them as headers to mule
     */
    @Override
    public Object transform(MuleMessage message, String outputEncoding) throws TransformerException {
        
    	// a feature of the cxf connector is that the input passes through the
    	// transformer twice. So we only try to transform the data when an
    	// inputStream is received
    	if (message.getPayload() instanceof InputStream) {
    		// first get all the data, and copy it to a bytearray
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            System.out.println(message.getPayload());
            try {
                IOUtils.copyLarge((InputStream) message.getPayload(), bOut);
            } catch (IOException e) {
                throw new TransformerException(this,e);
            }
            
            // create a new message that we can parse based on the bytes
            MessageImpl m = new MessageImpl();
            m.setContent(InputStream.class, new ByteArrayInputStream(bOut.toByteArray()));
            SoapMessage soapMessage = new SoapMessage(m);
            
            // use the readheaders interceptor from CXF to parse all the headers
            ReadHeadersInterceptor interceptor = new ReadHeadersInterceptor(BusFactory.getDefaultBus());
            interceptor.handleMessage(soapMessage);
            
            // just check whether the namespace ends with addressing, if so add the header to our mulemessage
            List<Header> headers = soapMessage.getHeaders();
            for (Header header : headers) {
                String namespace = header.getName().getNamespaceURI();
                if (namespace.endsWith("addressing")) {
                    message.setProperty(header.getName().getLocalPart(), header.getObject());
                }
            }
            // Add a new inputstream to the message so the rest of the processing isn't interrupted
            message.setPayload(new ByteArrayInputStream(bOut.toByteArray()));
            return message;
    	} else {
    		return message;
    	}
    }
}
