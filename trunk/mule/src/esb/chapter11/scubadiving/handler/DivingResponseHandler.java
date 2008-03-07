package esb.chapter11.scubadiving.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import esb.chapter11.scubadiving.model.DivingBooking;
import esb.chapter11.scubadiving.model.ScubaDivingBooking;
import esb.util.framework.JiBXUtil;

public class DivingResponseHandler implements ActionHandler {
	
	private static final long serialVersionUID = 4718429122080279872L;
	private static final Log log = LogFactory.getLog(DivingResponseHandler.class);

	public void execute(ExecutionContext execContext) throws Exception {
		log.error("DivingResponseHandler!!!!!!!!!!!!!!!!!!!!!!!");
		DivingBooking divingBooking = (DivingBooking) JiBXUtil.unmarshall(
			(String) execContext.getContextInstance().getVariable("divingResponse"), DivingBooking.class);
		ScubaDivingBooking booking = new ScubaDivingBooking();
		booking.setDivingBookingID(divingBooking.getDivingBookID());
		booking.setDivingStartingTime(divingBooking.getDivingStartingTime());
		booking.setDivingEndTime(divingBooking.getDivingEndTime());
		booking.setInstructorName(divingBooking.getInstructorName());
		booking.setTotalPrice(divingBooking.getPrice());
		booking.setSchoolAddress(divingBooking.getAddress());
		booking.setSchoolName(divingBooking.getName());
		execContext.getContextInstance().setVariable(
				"bookingResponse", booking);
	}
}
