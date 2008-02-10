package esb.chapter6;

import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.transformer.AbstractTransformer;

public class IntTransformer extends AbstractTransformer {

    public IntTransformer() {
        registerSourceType(String.class);
        setReturnClass(Object[].class);
    }
    
    
    @Override
    public Object doTransform(Object src, String encoding) throws TransformerException {
        String[] splitContent = ((String)src).split(",");
        
        if (!(splitContent.length == 2)) {
            throw new TransformerException(MessageFactory.createStaticMessage(
                    "Expected two values found: " + splitContent.length));
            
        }
        
        Object[] result = new Object[splitContent.length];
        for (int i = 0 ; i < splitContent.length ; i++) {
            try {
                result[i] = Integer.parseInt(splitContent[i]);
            } catch (Exception e) {
                throw new TransformerException(MessageFactory.createStaticMessage(
                        "unparsable value:  " + i));
            }
        }
        
        return result;
    }
}
