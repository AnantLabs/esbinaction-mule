package esb.chapter4.messageflow.transformer;

import org.mule.transformers.AbstractTransformer;

public class ISBNTransformer extends AbstractTransformer {

	protected Object doTransform(Object src, String encoding) {
		String isbn = (String) src;
		return "<isbn>" + isbn + "</isbn>";
	}

}
