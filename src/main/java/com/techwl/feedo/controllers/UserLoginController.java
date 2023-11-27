package com.techwl.feedo.controllers;

import static com.techwl.feedo.constants.FeedoConstants.EMPTY_STRING;
import static com.techwl.feedo.constants.FeedoConstants.FAILURE;
import static com.techwl.feedo.constants.FeedoConstants.SUCCESS;
import static com.techwl.feedo.constants.FeedoConstants.USER_CREATED;
import static com.techwl.feedo.constants.FeedoConstants.USER_CREATION_FAILED;
import static com.techwl.feedo.constants.FeedoConstants.USER_EMAIL_EXISTS;
import static com.techwl.feedo.constants.FeedoConstants.USER_INVALID;
import static com.techwl.feedo.constants.FeedoConstants.USER_VALID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techwl.feedo.dtos.LoginRequestDTO;
import com.techwl.feedo.handlers.ResponseHandler;
import com.techwl.feedo.jpa.repositories.UserRepository;
import com.techwl.feedo.user.entities.UserDetails;
import com.techwl.feedo.utils.FeedoStringTokensGenerator;

@RestController
@RequestMapping(path = "/user", produces = "application/json", consumes = "application/json")
public class UserLoginController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ResponseHandler responseHandler;

	@PostMapping(path = "login")
	public ResponseEntity<String> validateLogin(@RequestBody LoginRequestDTO loginRequest) {
		UserDetails user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
		ResponseEntity<String> response = null;
		if (user != null) {
			String token = FeedoStringTokensGenerator.generateFeedoToken(user.getEmail(),user.getPassword());
			 response = responseHandler.getResponse(USER_VALID, SUCCESS, EMPTY_STRING, token);
		}else {
			response = responseHandler.getResponse(USER_INVALID, SUCCESS, EMPTY_STRING);
		}
		return response;
	}

	@PostMapping(path = "create")
	public ResponseEntity<String> createUser(@RequestBody LoginRequestDTO loginRequest) {
		ResponseEntity<String> response = null;
		try {
			if (validateUser(loginRequest)) {
				return responseHandler.getResponse(USER_CREATION_FAILED, FAILURE, USER_EMAIL_EXISTS);
			}
			String tempPassword = FeedoStringTokensGenerator
					.generateBase64RandomPassword(loginRequest.getEmail() + loginRequest.getName());
			loginRequest.setPassword(tempPassword);
			UserDetails user = prepareUserData(loginRequest);
			userRepository.save(user);
			response = responseHandler.getResponse(USER_CREATED, SUCCESS, null);
		} catch (Exception ex) {
			response = responseHandler.getResponse(USER_CREATION_FAILED, FAILURE, ex.getMessage());
		}
		return response;

	}	
	
	@PostMapping(path = "resetPassword")
	public ResponseEntity<String> resetUserPassword(@RequestBody LoginRequestDTO user) {
		ResponseEntity<String> response = null;
		try {
			if (validateUser(user)) {
				return responseHandler.getResponse(USER_INVALID, FAILURE, USER_EMAIL_EXISTS);
			}
			
			String tempPassword = FeedoStringTokensGenerator
					.generateBase64RandomPassword(user.getEmail() + user.getName());
			user.setPassword(tempPassword);
			
			response = responseHandler.getResponse(USER_CREATED, SUCCESS, null);
		} catch (Exception ex) {
			response = responseHandler.getResponse(USER_CREATION_FAILED, FAILURE, ex.getMessage());
		}
		return response;

	}


	private boolean validateUser(final LoginRequestDTO user) {
		UserDetails tempUser = userRepository.findByEmail(user.getEmail());
		if (tempUser == null) {
			tempUser = userRepository.findByName(user.getName());
		}
		return tempUser != null;
	}
	
	private UserDetails prepareUserData(LoginRequestDTO loginRequest) {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmail(loginRequest.getEmail());
		userDetails.setName(loginRequest.getName());
		userDetails.setMobile(loginRequest.getMobile());
		userDetails.setPassword(loginRequest.getPassword());
		return userDetails;		
}
	

}
