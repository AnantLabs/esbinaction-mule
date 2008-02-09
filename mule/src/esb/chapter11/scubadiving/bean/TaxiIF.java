package esb.chapter11.scubadiving.bean;

import esb.chapter11.scubadiving.model.TaxiBooking;
import esb.chapter11.scubadiving.model.TaxiRequest;

public interface TaxiIF {

	public TaxiBooking processTaxiRequest(TaxiRequest request);
}
