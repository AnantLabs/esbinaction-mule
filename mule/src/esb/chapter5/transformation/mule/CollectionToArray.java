package esb.chapter5.transformation.mule;

import java.util.Collection;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class CollectionToArray extends AbstractTransformer {

	protected Object doTransform(Object src, String encoding) throws TransformerException {
		System.out.println("src " + src);
		if (src instanceof Collection) {
			Collection collection = (Collection) src;
			return collection.toArray();
		} else {
			throw new TransformerException(this, new javax.xml.transform.TransformerException(
					"Only Collection payload is supported."));
		}
	}

}
