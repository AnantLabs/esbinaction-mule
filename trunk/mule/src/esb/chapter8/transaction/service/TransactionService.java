package esb.chapter8.transaction.service;

import javax.jms.JMSException;

import org.mule.RequestContext;
import org.mule.api.MuleEventContext;
import org.mule.api.transaction.Transaction;

public class TransactionService {
	
	public String processMessage(String message) throws Exception {
		if(!"bad transaction".equalsIgnoreCase(message)) {
			return "transaction succeeded";
		}
		MuleEventContext context = RequestContext.getEventContext();
		Transaction transaction = context.getCurrentTransaction();
		try {
			transaction.setRollbackOnly();
		} catch(Exception e) {
			e.printStackTrace();
		}
		throw new JMSException("The transaction has failed");
	}

}
