package com.liveasyassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
/*
	@PutMapping("/retrieveNote")
	public ResponseEntity<Response> retrievingNote(@RequestHeader String token, @RequestParam long noteId) {

		Response responseStatus = noteService.retrieveNote(token, noteId);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.OK);
	}

	@PutMapping("/deleteNote")
	public ResponseEntity<Response> deletingNote(@RequestHeader String token, @RequestParam long noteId) {
		Response responseStatus = noteService.deleteNote(token, noteId);
		return new ResponseEntity<Response>(responseStatus, HttpStatus.OK);
	}

	@PutMapping("/permenantlyDelete")
	public ResponseEntity<Response> permenantdeletingNote(@RequestHeader String token, @RequestParam long noteId) {
		Response responsestatus = noteService.deleteNotePermenantly(token, noteId);
		return new ResponseEntity<Response>(responsestatus, HttpStatus.OK);

	}
	
	@PutMapping("/pinned")
	public ResponseEntity<Response> pinnedOrNot(@RequestHeader String token ,@RequestParam long noteId)
	{
		Response responseStatus = noteService.checkPinOrNot(token, noteId);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);	
	}
	
	@PutMapping("/archieved")
	public ResponseEntity<Response> archievedOrNot(@RequestHeader String token, @RequestParam long noteId)
	{
		Response responseStatus = noteService.checkArchieveOrNot(token, noteId);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);	
	}
	
	@PutMapping("/color")
	public ResponseEntity<Response> colorSet(@RequestHeader String token, @RequestParam long noteId, @RequestParam String color)
	{
		Response responseStatus = noteService.setColour(token, noteId, color);
		return new ResponseEntity<Response>(responseStatus,HttpStatus.OK);
		
	}
	
	@GetMapping("/getTrash")
	public List<Note> getTrashNotes(@RequestHeader String token) {
		List<Note> listnotes = noteService.restoreTrashNotes(token);
		return listnotes;
	}
	

	@GetMapping("/getPin")
	public List<Note> getPinnedNotes(@RequestHeader String token) {
		List<Note> listnotes = noteService.getPinnedNote(token);
		return listnotes;
	}
	
	@GetMapping("/getArchieve")
	public List<Note> getArchieveNotes(@RequestHeader String token) {
		List<Note> listnotes = noteService.getArchievedNote(token);
		return listnotes;
	}
	
	@GetMapping("/sortByName")
	public List<Note> sortName(@RequestHeader String token){
		List<Note> listnote=noteService.sortByTitle(token);
		return listnote;
	}
	
	@GetMapping("/sortByDate")
	public List<Note> sortDate(@RequestHeader String token){
		List<Note> listNote =noteService.sortByDate(token);
		return listNote;
	}
	//Complete Collabrator
}
 */