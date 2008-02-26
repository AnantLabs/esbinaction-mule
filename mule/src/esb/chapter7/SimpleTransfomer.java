package esb.chapter7;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class SimpleTransfomer extends AbstractTransformer {

    @Override
    protected Object doTransform(Object src, String encoding) throws TransformerException {
        String outputMessage = ToStringBuilder.reflectionToString(src,ToStringStyle.DEFAULT_STYLE);
        return outputMessage;
    }
}
