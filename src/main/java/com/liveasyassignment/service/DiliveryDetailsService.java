package com.liveasyassignment.service;

import com.liveasyassignment.model.DeliveryDetailsModel;
import com.liveasyassignment.response.Response;

public interface DiliveryDetailsService {
	
	public Response createDeliveryDetails(DeliveryDetailsModel deliveryDetails);
	
	public Response updateDeliveryDetails(DeliveryDetailsModel deliveryDetails, long loadId);
	
	public Response retriveDeliverDetails(long loadId);
	
	public Response deleteDeliveryDetails(long loadId);
}
