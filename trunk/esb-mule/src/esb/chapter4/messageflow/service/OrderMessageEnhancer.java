package esb.chapter4.messageflow.service;

import org.apache.log4j.Logger;

import esb.chapter4.messageflow.Order;

public class OrderMessageEnhancer {
	
	private static final Logger logger = Logger.getLogger(OrderMessageEnhancer.class);
	
	public InvoiceOrder enhanceOrder(Order order) {
		logger.info("Received order = " + order);
		InvoiceOrder invoiceOrder = new InvoiceOrder();
		invoiceOrder.setOrderID(order.getOrderID());
		invoiceOrder.setAmount(order.getAmount());
		invoiceOrder.setClientNumber(order.getClientNumber());
		invoiceOrder.setOrderDescription(order.getOrderDescription());
		invoiceOrder.setClientname("SpecialCompany");
		invoiceOrder.setAddress("Street 45");
		invoiceOrder.setCity("New York");
		return invoiceOrder;
	}

}
