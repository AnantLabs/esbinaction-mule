package esb.chapter3;

import java.util.Map;

import org.apache.log4j.Logger;

public class LoggerComponent {
	
	private final static Logger logger = Logger.getLogger(LoggerComponent.class);
	private Map<Integer, Integer> customerNumberMap;
	
	public Person log(Person person) {
		logger.info("received person " + person.getCustomerNumber() 
				+ ", " + person.getLastName());
		int customerNumber = person.getCustomerNumber();
		int changedNumber = customerNumberMap.get(customerNumber);
		person.setCustomerNumber(changedNumber);
		return person;
	}

	public void setCustomerNumberMap(Map<Integer, Integer> customerNumberMap) {
		this.customerNumberMap = customerNumberMap;
	}

}
