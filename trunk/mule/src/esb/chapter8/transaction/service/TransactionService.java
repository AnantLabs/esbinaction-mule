package esb.chapter8.transaction.service;

import org.mule.RequestContext;
import org.mule.api.MuleEventContext;
import org.mule.api.transaction.Transaction;

public class TransactionService {
	
	public String processMessage(String message) throws Exception {
		System.out.println("TransactionService received message " + message);
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
		throw new Exception("The transaction has failed");
	}

}
