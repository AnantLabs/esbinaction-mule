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
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.bus.CXFBusImpl;
import org.apache.cxf.headers.Header;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.MessageAdapter;
import org.mule.transformer.AbstractMessageAwareTransformer;
import org.mule.transport.cxf.CxfMessageAdapter;
import org.mule.transport.cxf.CxfMessageReceiver;
import org.mule.transport.cxf.CxfMuleSession;

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

    	
    		MessageAdapter adapter = message.getAdapter();
    		MessageImpl msg = (MessageImpl) ((CxfMessageAdapter)adapter).getOriginalPayload();
            
            SoapMessage soapMessage = new SoapMessage(msg);
            
            // use the readheaders interceptor from CXF to parse all the headers
            ReadHeadersInterceptor interceptor = new ReadHeadersInterceptor(BusFactory.getDefaultBus());
            interceptor.handleMessage(soapMessage);
            
            // just check whether the namespace ends with addressing, if so add the header to our mulemessage
            List<Header> headers = soapMessage.getHeaders();
            
            for (Header header : headers) {
                String namespace = header.getName().getNamespaceURI();
                if (namespace.endsWith("addressing")) {
                    message.setProperty(header.getName().getLocalPart(), header.getObject());
                    System.out.println("Found addressing header:" + header.getName().getLocalPart());
                }
            }
            // Add a new inputstream to the message so the rest of the processing isn't interrupted
          
            return message;
    }
}
