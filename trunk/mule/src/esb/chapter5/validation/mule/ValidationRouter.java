package esb.chapter5.validation.mule;

import java.io.InputStream;
import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.io.SAXReader;
import org.mule.api.MessagingException;
import org.mule.api.MuleEvent;
import org.mule.api.routing.InboundRouter;
import org.mule.api.transformer.TransformerException;
import org.mule.routing.inbound.ForwardingConsumer;
import org.mule.util.ExceptionUtils;
import org.mule.util.IOUtils;

public class ValidationRouter extends ForwardingConsumer implements InboundRouter {
	
	private final Log logger = LogFactory.getLog(getClass());
	// xml parser feature names for optional XSD validation
	private static final String APACHE_XML_FEATURES_VALIDATION_SCHEMA = "http://apache.org/xml/features/validation/schema";
    private static final String APACHE_XML_FEATURES_VALIDATION_SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    
    // JAXP property for specifying external XSD location
    private static final String JAXP_PROPERTIES_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    // JAXP properties for specifying external XSD language
    private static final String JAXP_PROPERTIES_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String JAXP_PROPERTIES_SCHEMA_LANGUAGE_VALUE = "http://www.w3.org/2001/XMLSchema";
    
	private volatile String externalSchemaLocation = null;

	public boolean isMatch(MuleEvent event) throws MessagingException {
		if (logger.isDebugEnabled()) {
            logger.debug("Validating incoming message.");
        }
		Object payload = null;
		try {
			payload = event.transformMessage();
		} catch(TransformerException e) {
			throw new IllegalArgumentException("Transformer failed: "
					+ ExceptionUtils.getStackTrace(e));
		}
		if(externalSchemaLocation == null) {
			logger.info("No schema provided, no validation will be performed.");
			return true;
		}
		logger.info("payload to be validated: " + payload);
		if (payload instanceof String) {
            String xml = (String) payload;
            SAXReader reader = new SAXReader();
            try {
	            setDoSchemaValidation(reader);
	            reader.read(new StringReader(xml));
            } catch(Exception e) {
            	logger.error("Failed to validate the payload", e);
            	return false;
            }
        } else {
        	throw new IllegalArgumentException("Expected message type of String");
        }
		return true;
	}
	
	private void setDoSchemaValidation(SAXReader reader) throws Exception {
        reader.setValidation(true);
        reader.setFeature(APACHE_XML_FEATURES_VALIDATION_SCHEMA, true);
        reader.setFeature(APACHE_XML_FEATURES_VALIDATION_SCHEMA_FULL_CHECKING, true);
        
        InputStream xsdAsStream = IOUtils.getResourceAsStream(getExternalSchemaLocation(), getClass());
        if (xsdAsStream == null) {
            throw new IllegalArgumentException("Couldn't find schema at "
            		+ getExternalSchemaLocation());
        }
        reader.setProperty(JAXP_PROPERTIES_SCHEMA_LANGUAGE, JAXP_PROPERTIES_SCHEMA_LANGUAGE_VALUE);
        reader.setProperty(JAXP_PROPERTIES_SCHEMA_SOURCE, xsdAsStream);
    }

	public String getExternalSchemaLocation() {
		return externalSchemaLocation;
	}

	public void setExternalSchemaLocation(String externalSchemaLocation) {
		this.externalSchemaLocation = externalSchemaLocation;
	}
}
