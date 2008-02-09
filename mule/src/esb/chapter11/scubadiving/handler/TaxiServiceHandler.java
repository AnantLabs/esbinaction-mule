package esb.chapter11.scubadiving.handler;

import org.apache.log4j.Logger;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import esb.chapter11.scubadiving.model.ScubaDivingBooking;
import esb.chapter11.scubadiving.model.ScubaDivingRequest;
import esb.chapter11.scubadiving.model.TaxiRequest;
import esb.util.framework.JiBXUtil;

public class TaxiServiceHandler implements ActionHandler {
	
	private static final long serialVersionUID = -2024118944718206007L;
	private final static Logger logger = Logger.getLogger(TaxiServiceHandler.class);

	public void execute(ExecutionContext execContext) throws Exception {
		logger.info("entering taxi service handler");
		ScubaDivingRequest bookingRequest = (ScubaDivingRequest) 
				execContext.getContextInstance().getVariable("booking");
		ScubaDivingBooking bookingResponse = (ScubaDivingBooking) 
				execContext.getContextInstance().getVariable("bookingResponse");
		TaxiRequest taxiRequest = new TaxiRequest();
		taxiRequest.setHotelName("SleepingBeauty");
		taxiRequest.setNumberOfTravelers(bookingRequest.getNumberOfDivers());
		taxiRequest.setDepartureTime(bookingResponse.getDivingStartingTime());
		taxiRequest.setReturnTime(bookingResponse.getDivingEndTime());
		taxiRequest.setDestinationAddress(bookingResponse.getSchoolAddress());
		execContext.getContextInstance().setVariable("taxiRequest", 
				JiBXUtil.marshall(taxiRequest));
	}
	
}
