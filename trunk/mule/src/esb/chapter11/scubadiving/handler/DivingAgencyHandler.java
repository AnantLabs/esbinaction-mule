package esb.chapter11.scubadiving.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import esb.chapter11.scubadiving.model.DivingRequest;
import esb.chapter11.scubadiving.model.ScubaDivingRequest;
import esb.util.framework.JiBXUtil;

public class DivingAgencyHandler implements ActionHandler {
	
	private static final long serialVersionUID = 4718429122080279872L;
	private static final Log log = LogFactory.getLog(DivingAgencyHandler.class);

	public void execute(ExecutionContext execContext) throws Exception {
		log.error("DivingAgencyHandler!!!!!!!!!!!!!!!!!!!!!!!");
		ScubaDivingRequest bookingRequest = (ScubaDivingRequest) 
			execContext.getContextInstance().getVariable("booking");
		DivingRequest divingRequest = new DivingRequest();
		divingRequest.setHotelName("SleepingBeauty");
		divingRequest.setBookingName(bookingRequest.getGuestName());
		divingRequest.setNumberOfDivers(bookingRequest.getNumberOfDivers());
		divingRequest.setRequestDate(bookingRequest.getRequestDate());
		execContext.getContextInstance().setVariable(
				"divingRequest", JiBXUtil.marshall(divingRequest));
	}
}
