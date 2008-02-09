package esb.chapter11.scubadiving.handler;

import org.apache.log4j.Logger;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import esb.chapter11.scubadiving.model.LunchBooking;
import esb.chapter11.scubadiving.model.ScubaDivingBooking;
import esb.chapter11.scubadiving.model.ScubaDivingRequest;
import esb.util.framework.JiBXUtil;

public class LunchServiceHandler implements ActionHandler {
	
	private static final long serialVersionUID = 3535253151503276667L;
	private final static Logger logger = Logger.getLogger(LunchServiceHandler.class);

	public void execute(ExecutionContext execContext) throws Exception {
		logger.info("entering lunch service handler");
		ScubaDivingRequest bookingRequest = (ScubaDivingRequest) 
				execContext.getContextInstance().getVariable("booking");
		LunchBooking lunchBooking = new LunchBooking();
		lunchBooking.setGuestName(bookingRequest.getGuestName());
		lunchBooking.setNumberOfLunches(bookingRequest.getNumberOfDivers());
		lunchBooking.setRoomNumber(bookingRequest.getRoomNumber());
		lunchBooking.setLunchDate(bookingRequest.getRequestDate());
		execContext.getContextInstance().setVariable("lunchBooking", 
				JiBXUtil.marshall(lunchBooking));
		ScubaDivingBooking bookingResponse = (ScubaDivingBooking) 
				execContext.getContextInstance().getVariable("bookingResponse");
		bookingResponse.setTotalPrice(bookingResponse.getTotalPrice() + 
				(14.95 * bookingRequest.getNumberOfDivers()));
	}
	
}
