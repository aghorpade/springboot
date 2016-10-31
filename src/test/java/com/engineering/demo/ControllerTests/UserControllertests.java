package com.engineering.demo.ControllerTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cardinalhealth.chh.pod.controller.UserController;
import com.cardinalhealth.chh.pod.model.User;
import com.cardinalhealth.chh.pod.models.in.UserInfo;
import com.cardinalhealth.chh.pod.models.out.UserOut;
import com.cardinalhealth.chh.pod.repositories.UserRepository;
import com.cardinalhealth.chh.pod.services.UserService;

public class UserControllertests {

	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	@Mock
	private UserRepository userRepo;
    
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	//test case for create user
	@Test
	public void createUser()
	{	UserInfo userInfo=new UserInfo("amit", "test", 9, "Demotest998*", "Lane 2", "llisio", "US", 9874298);
		UserOut userOut=new UserOut("23jds-32jds3-dkh73-djjd", "amit", "test", 9, "Lane 2", "llisio", "US", 9874298);
		when(userService.createUser(userInfo)).thenReturn(userOut);
		UserOut userResponse=userController.createUser(userInfo);
		assertNotNull(userResponse);
		assertEquals(userResponse, userOut);
	}
	
	//test get user service
	@Test
	public void getUser(){
		
		User user=new User(1l,"23jds-32jds3-dkh73-djjd","amit", "test", "Demotest998*", 9, "Lane 2", "llisio", "US", 9874298);
		when(userService.getUser("23jds-32jds3-dkh73-djjd")).thenReturn(user);
		User userout=userController.getUserByGuid("23jds-32jds3-dkh73-djjd");
		assertNotNull(userout);
		assertEquals(userout, user);
		
	}
	
	
}
