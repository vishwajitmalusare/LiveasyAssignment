package com.liveasyassignment.service;

import java.text.SimpleDateFormat;
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
		LocalDateTime actualDate = LocalDateTime.now();
		
		DeliveryDetailsModel deliverDetailsObject = modelMapperObj.map(deliveryDetails, DeliveryDetailsModel.class);
		Optional<DeliveryDetailsModel> obj = deliveryDetailsRepo.findById(id);
		deliverDetailsObject.setLoadId(id);
		deliverDetailsObject.setDate(actualDate);
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
		// TODO Auto-generated method stub
		return null;
	}

}
/*
	@Override
	public Response retrieveNote(String token, long noteId) {

		long id = userToken.decodeToken(token);
		Note note = noteRepository.findByUserIdAndNoteId(id, noteId);
		String title = note.getTitle();

		Response response = ResponseHelper.statusResponse(300, "retrieved successfully");
		return response;
	}

	public Response deleteNote(String token, long noteId) {
		long id = userToken.decodeToken(token);

		Note note = noteRepository.findByUserIdAndNoteId(id, noteId);

		if (note.isTrash() == false) {
			note.setTrash(true);
			note.setModified(LocalDateTime.now());
			noteRepository.save(note);
			Response response = ResponseHelper.statusResponse(100, environment.getProperty("status.note.trashed"));
			return response;
		}

		Response response = ResponseHelper.statusResponse(100, environment.getProperty("status.note.trashError"));
		return response;
	}

	public Response deleteNotePermenantly(String token, long noteId) {

		long id = userToken.decodeToken(token);

		Optional<User> user = userRepository.findById(id);
		Note note = noteRepository.findByUserIdAndNoteId(id, noteId);

		if (note.isTrash() == true) {
			user.get().getNotes().remove(note);
			userRepository.save(user.get());
			noteRepository.delete(note);
			Response response = ResponseHelper.statusResponse(200, environment.getProperty("status.note.deleted"));
			return response;
		} else {
			Response response = ResponseHelper.statusResponse(100, environment.getProperty("status.note.noteDeleted"));
			return response;
		}
	}
	
	 @Override
	public Response checkPinOrNot(String token, long noteId) {

		long userId = userToken.decodeToken(token);
		System.out.println(userId);
		Note note = noteRepository.findByUserIdAndNoteId(userId, noteId);
		System.out.println("note is" + note);

		if (note == null) {
			throw new UserException(100, "note is not exist");
		}

		if (note.isPin() == false) {
			note.setPin(true);
			noteRepository.save(note);

			Response response = ResponseHelper.statusResponse(200, environment.getProperty("status.note.pinned"));
			return response;
		} else {
			note.setPin(false);
			noteRepository.save(note);
			Response response = ResponseHelper.statusResponse(200, environment.getProperty("status.note.unpinned"));
			return response;
		}
	}

	@Override
	public Response checkArchieveOrNot(String token, long noteId) {

		long userId = userToken.decodeToken(token);
		Note note = noteRepository.findByUserIdAndNoteId(userId, noteId);

		if (note == null) {
			throw new UserException(100, "note is not exist");
		}

		if (note.isArchieve() == false) {
			note.setArchieve(true);
			noteRepository.save(note);

			Response response = ResponseHelper.statusResponse(200, environment.getProperty("status.note.archieved"));
			return response;
		} else {
			note.setArchieve(false);
			noteRepository.save(note);

			Response response = ResponseHelper.statusResponse(200, environment.getProperty("status.note.unarchieved"));
			return response;
		}

	}

	@Override
	public Response setColour(String token, long noteId, String color) {

		long userId = userToken.decodeToken(token);
		Note note = noteRepository.findByUserIdAndNoteId(userId, noteId);
		if (note == null) {
			throw new UserException(100, "invalid note or not exist");
		}

		note.setColour(color);
		noteRepository.save(note);

		Response response = ResponseHelper.statusResponse(200, environment.getProperty("status.note.color"));
		return response;
	}


	@Override
	public List<Note> restoreTrashNotes(String token) {

		long userId = userToken.decodeToken(token);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("Sorry ! Note are not available"));
		List<Note> userNote = user.getNotes().stream().filter(data -> (data.isTrash() == true))
				.collect(Collectors.toList());
		
		return userNote;

	}


	@Override
	public List<Note> getPinnedNote(String token) {
		long userId = userToken.decodeToken(token);

		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("No note is available"));
		List<Note> pinNote = user.getNotes().stream().filter(data -> (data.isPin() == true))
				.collect(Collectors.toList());
		user.getNotes().stream().filter(data1 -> (data1.isPin() == true)).collect(Collectors.toList()).forEach(System.out::println);
		
		return pinNote;

	}

	@Override
	public List<Note> getArchievedNote(String token) {
		long userId = userToken.decodeToken(token);

		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("No note is available"));
		List<Note> archieveNote = user.getNotes().stream().filter(data -> (data.isArchieve() == true))
				.collect(Collectors.toList());

		user.getNotes().stream().filter(data1 -> (data1.isArchieve() == true)).collect(Collectors.toList()).forEach(System.out::println);
		return archieveNote;

	}

	@Override
	public List<Note> sortByTitle(String token) {
		
			long userId = userToken.decodeToken(token);
			Optional<User> user= userRepository.findById(userId);
//			List<Note> updateList = new ArrayList<Note>();
			if(user.isPresent()) {
				List<Note> noteList = noteRepository.findAll();
				for(int i=0; i<noteList.size();i++) {
					for(int j=0;j<noteList.size()-1;j++) {
						if(noteList.get(i).getTitle().compareTo(noteList.get(j).getTitle()) > 0) {
							Note note = noteList.get(i);
							noteList.set(i, noteList.get(j));
							noteList.set(j, note);
						}
					}
				}
				return noteList;
			}else {
				throw new UserException("User not found");
			}
		}
	@Override
	public List<Note> sortByDate(String token) {
		// TODO Auto-generated method stub
		long userId =userToken.decodeToken(token);
		Optional<User> user =userRepository.findById(userId);
		if(user.isPresent()) {
			List<Note> noteList = noteRepository.findAll();
			for(int i=0;i<noteList.size();i++) {
				for(int j=0;j<noteList.size()-1;j++) {
					if(noteList.get(i).getCreated().compareTo(noteList.get(i).getCreated())<0) {
						Note note = noteList.get(i);
						noteList.set(i,noteList.get(i));
						noteList.set(j, note);
					}
				}
			}
			return noteList;
		}else {
			throw new UserException("User not present");
		}
	}

	@Override
	public Response addColleaborator(String token, String email, long noteId) {
		// TODO Auto-generated method stub
		Email collabEmail = new Email();
		long userId = userToken.decodeToken(token);
		Optional<User> mainUser = userRepository.findById(userId);
		Optional<User> user = userRepository.findByEmailId(email);
		
		if(!user.isPresent()) 
			throw new UserException(-4,"No user exist");
		Note note = noteRepository.findByUserIdAndNoteId(userId, noteId);
		
		if(note == null) 
			throw new UserException(-5,"No note exist");
		
		if(user.get().getCollaboratedNotes().contains(note))
			throw new UserException(-5,"Note already Collaborated");
		
		user.get().getCollaboratedNotes().add(note);
		note.getCollaboratedUser().add(user.get());
		
		userRepository.save(user.get());
		noteRepository.save(note);
		
		collabEmail.setFrom("iamvish.net@gmail.com");
		collabEmail.setTo(email);
		collabEmail.setSubject("Note from "+mainUser.get().getEmailId() +"collabortaed to you\nTitle :"+note.getTitle()
		+"\nDescription :"+note.getDescription());
		Response response = ResponseHelper.statusResponse(200, environment.getProperty("status.collab.success"));
		return response;
	}

	@Override
	public Response deleteCollaboratorToNote(String token, long noteId, String emailId) {
		// TODO Auto-generated method stub
		long userId = userToken.decodeToken(token);
		Optional<User> user = userRepository.findByEmailId(emailId);
		
		if(!user.isPresent())
			throw new UserException(-4,"No user exist ");
		
		Note note = noteRepository.findByUserIdAndNoteId(userId, noteId);
		
		if(note == null)
			throw new UserException(-5,"No note Exist");
		user.get().getCollaboratedNotes().remove(note);
		note.getCollaboratedUser().remove(user.get());
	
	
		Response response = ResponseHelper.statusResponse(100, environment.getProperty("status.note.trashError"));
		return response;
	}

}
*
 */