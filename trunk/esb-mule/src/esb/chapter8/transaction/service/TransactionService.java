package esb.chapter8.transaction.service;

import javax.jms.JMSException;

import org.mule.impl.RequestContext;
import org.mule.umo.UMOEventContext;
import org.mule.umo.UMOTransaction;

public class TransactionService {
	
	public String processMessage(String message) throws Exception {
		if(!"bad transaction".equalsIgnoreCase(message)) {
			return "transaction succeeded";
		}
		UMOEventContext context = RequestContext.getEventContext();
		UMOTransaction transaction = context.getCurrentTransaction();
		try {
			transaction.setRollbackOnly();
		} catch(Exception e) {
			e.printStackTrace();
		}
		throw new JMSException("The transaction has failed");
	}

}
