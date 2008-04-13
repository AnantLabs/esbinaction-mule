package esb.chapter7;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.staxutils.StaxUtils;
import org.mule.api.MuleMessage;
import org.mule.api.config.MuleProperties;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.transformer.AbstractMessageAwareTransformer;
import org.w3c.dom.Document;

public class MethodSelector extends AbstractMessageAwareTransformer  {

    /**
     * We're interested in the root element, since that 
     * determines the method we want to invoke.
     */
    @Override
    public Object transform(MuleMessage message, String outputEncoding) throws TransformerException {
        
        String data = (String) message.getPayload();
        
        try {
            Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(data.getBytes()));
            String nodeName = d.getFirstChild().getNodeName();
            message.setProperty(MuleProperties.MULE_METHOD_PROPERTY, nodeName.substring(nodeName.indexOf(':')+1));
        } catch (Exception e) {
            throw new TransformerException(MessageFactory.createStaticMessage("Error while creating document: " + e.getMessage()));
        }
        
        XMLStreamReader reader = StaxUtils.createXMLStreamReader(new StringReader(data));
        message.setPayload(reader);
        
        return reader;
    }
}
