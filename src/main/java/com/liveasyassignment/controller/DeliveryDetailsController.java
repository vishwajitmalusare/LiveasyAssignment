package com.liveasyassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liveasyassignment.model.DeliveryDetailsModel;
import com.liveasyassignment.response.Response;
import com.liveasyassignment.service.DiliveryDetailsService;

@RestController
@RequestMapping("/deliverydetails")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class DeliveryDetailsController {

	@Autowired
	private DiliveryDetailsService deliveryDetailsService;
	
	@PostMapping("/createDeliveryDetails")
	public ResponseEntity<Response> createDetailDelivery(@RequestBody DeliveryDetailsModel delieryDetails){
		System.out.println("DeliveryDetailsController");
		Response response = deliveryDetailsService.createDeliveryDetails(delieryDetails);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateDelieryDetails")
	public ResponseEntity<Response> updateingDeliveryDetails(@RequestBody DeliveryDetailsModel deliveryDetails, @RequestParam long loadId){
		Response responseStatus = deliveryDetailsService.updateDeliveryDetails(deliveryDetails, loadId);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/retriveDeliveryDetails")
	public ResponseEntity<Response> retrivingDeliveryDetails(@RequestParam long loadId) {
		Response responseStatus = deliveryDetailsService.retriveDeliverDetails(loadId);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteDeliverDetails")
	public ResponseEntity<Response> deletingDeliveryDetails(@RequestParam long loadId) {
		Response response = deliveryDetailsService.deleteDeliveryDetails(loadId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}