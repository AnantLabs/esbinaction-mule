package esb.chapter8.transaction.service;

public class TransactionService {
	
	public String processMessage(String message) throws Exception {
		System.out.println("TransactionService received message " + message);
		if(!"bad transaction".equalsIgnoreCase(message)) {
			return "transaction succeeded";
		} else {
			throw new Exception("The transaction will fail");
		}
	}

}
