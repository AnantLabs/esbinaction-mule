package esb.chapter6;

import esb.chapter6.ejb.SearchQuery;

import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.transformer.AbstractTransformer;

public class QueryTransformer extends AbstractTransformer {

    public QueryTransformer() {
        registerSourceType(String.class);
        setReturnClass(SearchQuery.class);
    }
    
    
    @Override
    public Object doTransform(Object src, String encoding) throws TransformerException {
        String[] splitContent = ((String)src).split(",");
        
        if (!(splitContent.length == 2)) {
            throw new TransformerException(MessageFactory.createStaticMessage(
                    "Expected two values, found: " + splitContent.length));
        }
        
        return new SearchQuery(splitContent[0],splitContent[1]);
    }
}
