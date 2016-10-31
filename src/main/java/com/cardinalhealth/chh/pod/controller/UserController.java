package com.cardinalhealth.chh.pod.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cardinalhealth.chh.pod.model.User;
import com.cardinalhealth.chh.pod.models.in.UserInfo;
import com.cardinalhealth.chh.pod.models.out.UserOut;
import com.cardinalhealth.chh.pod.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	public UserService userService;
	
	 @Value("${application.message:Hello World}")
		private String message = "Hello World";

	// REST APi to create user/save user in database
	@ApiOperation(nickname = "createUser", value = "Create user", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User created successfully") })
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public UserOut createUser(@Validated @RequestBody UserInfo userInfo) {
		// call user servvice to create user
		return userService.createUser(userInfo);
	}

	// REST API to get user by guid
	@ApiOperation(nickname = "getUserByGuid", value = "Get User ", notes = "Get User by its unique guid", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "User with guid not found") })
	@RequestMapping(value = "/{guid}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUserByGuid(
			@ApiParam(name = "guid", value = "Guid of user", required = true) @PathVariable("guid") String guid) {

		return userService.getUser(guid);

	}

	// REST API to get all users
	@ApiOperation(nickname = "getUsers", value = "Get All Users ", notes = "Get All User", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Users not available") })
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getAllUsers() {

		return userService.getAllUsers();

	}

	// REST API to delete user by guid
	@ApiOperation(nickname = "deleteUserByGuid", value = "Delete User ", notes = "Delete User by its unique guid", httpMethod = "DELETE")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "User with guid not found") })
	@RequestMapping(value = "/{guid}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteUserByGuid(
			@ApiParam(name = "guid", value = "Guid of user", required = true) @PathVariable("guid") String guid) {

		userService.deleteUser(guid);

	}
    
}

