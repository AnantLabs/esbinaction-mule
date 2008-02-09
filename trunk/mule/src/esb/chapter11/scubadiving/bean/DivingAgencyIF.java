package esb.chapter11.scubadiving.bean;

import esb.chapter11.scubadiving.model.DivingBooking;
import esb.chapter11.scubadiving.model.DivingRequest;

public interface DivingAgencyIF {
	
	public DivingBooking processBooking(DivingRequest request);

}
