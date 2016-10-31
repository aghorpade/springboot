package com.cardinalhealth.chh.pod.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardinalhealth.chh.pod.model.User;
import com.cardinalhealth.chh.pod.models.in.UserInfo;
import com.cardinalhealth.chh.pod.models.out.UserOut;
import com.cardinalhealth.chh.pod.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepo;
	
	
	// method to create user on database user table
	public UserOut createUser(UserInfo userRequest) {
		// validate incoming request parameters
		
		// map request with userDAO
		User userIn = mapProperties(userRequest);

		UserOut userResponse = new UserOut(userIn.getGuid(), userIn.getFirstName(),
				userIn.getLastName(), userIn.getAge(), userIn.getAddress(), userIn.getState(), userIn.getCountry(),
				userIn.getMobileNumber());
		// return back response user object
		return userResponse;
	}

	private User mapProperties(UserInfo userInfo) {
		User user = new User(userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPassword(), userInfo.getAge(),
				userInfo.getAddress(), userInfo.getState(), userInfo.getCountry(), userInfo.getMobileNumber());
		//save user into database
		User createdUser=userRepo.save(user);
		return createdUser;
	}
	
	
	public User getUser(String guid){
		
		return userRepo.findByGuid(guid);
	}
	
	
	public List<User> getAllUsers(){
		
		return userRepo.findAll();
	}
	
	public void deleteUser(String guid){
		
		User userToBeDeleted=getUser(guid);
		if(userToBeDeleted != null){
			userRepo.delete(userToBeDeleted);
		}else{
			throw new RuntimeException("User not present");
		}
		
	}
	
	

}
