package esb.chapter11.scubadiving.handler;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

import esb.chapter11.scubadiving.model.ScubaDivingBooking;
import esb.chapter11.scubadiving.model.TaxiBooking;
import esb.util.framework.JiBXUtil;

public class TaxiResponseHandler implements ActionHandler {
	
	private static final long serialVersionUID = 4718429122080279872L;

	public void execute(ExecutionContext execContext) throws Exception {
		TaxiBooking taxiBooking = (TaxiBooking) JiBXUtil.unmarshall(
			(String) execContext.getContextInstance().getVariable("taxiResponse"), TaxiBooking.class);
		ScubaDivingBooking booking = new ScubaDivingBooking();
		booking.setTaxiBookingID(taxiBooking.getTaxiBookingID());
		booking.setTaxiDepartureTime(taxiBooking.getTaxiDepartureTime());
		booking.setTaxiReturnTime(taxiBooking.getTaxiReturnTime());
		booking.setTotalPrice(booking.getTotalPrice() + taxiBooking.getPrice());
		execContext.getContextInstance().setVariable(
				"bookingResponse", booking);
	}
}
