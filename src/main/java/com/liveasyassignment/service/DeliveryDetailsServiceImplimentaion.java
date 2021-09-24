package com.liveasyassignment.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.liveasyassignment.model.DeliveryDetailsModel;
import com.liveasyassignment.repository.DiliveryDetailsRepo;
import com.liveasyassignment.response.Response;
import com.liveasyassignment.response.ResponseHelper;
import com.liveasyassignment.response.UserException;

@Service("deleveryDetailsService")
public class DeliveryDetailsServiceImplimentaion implements DiliveryDetailsService {

	
	@Autowired
	private DiliveryDetailsRepo deliveryDetailsRepo;
	
	@Autowired
	private ModelMapper modelMapperObj;
	
	@Autowired
	private Environment environment;
	
	@Override
	public Response createDeliveryDetails(DeliveryDetailsModel deliveryDetails) {
		// TODO Auto-generated method stub
		
		long id = deliveryDetails.getLoadId();
		
		DeliveryDetailsModel deliverDetailsObject = modelMapperObj.map(deliveryDetails, DeliveryDetailsModel.class);
		Optional<DeliveryDetailsModel> obj = deliveryDetailsRepo.findById(id);
		deliverDetailsObject.setLoadId(id);
		deliverDetailsObject.setDate(LocalDateTime.now());
		deliveryDetailsRepo.save(deliverDetailsObject);
		
		Response response = ResponseHelper.statusResponse(100, environment.getProperty("CreatedSuccessfull"));
		
		return response;

	}

	@Override
	public Response updateDeliveryDetails(DeliveryDetailsModel deliveryDetails, long longId) {
		if (deliveryDetails.getLoadPoint().isEmpty() && deliveryDetails.getProductType().isEmpty()) {
			throw new UserException("Delivery details are looks empty !!!");
		}
		
		DeliveryDetailsModel newDeliveryDetails = deliveryDetailsRepo.findByLoadId(longId);
		newDeliveryDetails.setLoadPoint(deliveryDetails.getLoadPoint());
		newDeliveryDetails.setUnloadingPoint(deliveryDetails.getUnloadingPoint());
		newDeliveryDetails.setNoOfTrucks(deliveryDetails.getNoOfTrucks());
		newDeliveryDetails.setProductType(deliveryDetails.getProductType());
		newDeliveryDetails.setTruckType(deliveryDetails.getTruckType());
		newDeliveryDetails.setShipperId(deliveryDetails.getShipperId());
		newDeliveryDetails.setWeight(deliveryDetails.getWeight());
		newDeliveryDetails.setDate(LocalDateTime.now());
		newDeliveryDetails.setOptional(deliveryDetails.getOptional());
		deliveryDetailsRepo.save(newDeliveryDetails);
		
		Response response = ResponseHelper.statusResponse(200, "Updates Successfully !!!!");
		return response;
	}

	@Override
	public Response retriveDeliverDetails(long loadId) {
		DeliveryDetailsModel newDeliveryDetails = deliveryDetailsRepo.findByLoadId(loadId);
		
		Response response = ResponseHelper.statusResponse(300, "Retrived "+
		newDeliveryDetails.toString());
		return response;
	}

	@Override
	public Response deleteDeliveryDetails(long loadId) {
		DeliveryDetailsModel obj = deliveryDetailsRepo.findByLoadId(loadId);
		deliveryDetailsRepo.delete(obj);
		Response response = ResponseHelper.statusResponse(200, "Deleted succesfully");
		return response;
	}

}