package com.pedrodlf.iobuilders.bank.user.adapter.rest;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrodlf.iobuilders.api.UsersApi;
import com.pedrodlf.iobuilders.model.UserRequest;
import com.pedrodlf.iobuilders.model.UserResponse;

@RestController
@RequestMapping("/v1")
public class UserControllerImpl implements UsersApi{

	Logger log = LoggerFactory.getLogger(UserControllerImpl.class);
	
    private UserAdapter userAdapter;

    public UserControllerImpl(UserAdapter userAdapter){
        this.userAdapter = userAdapter;
    }

    public ResponseEntity<UserResponse> usersPost(UserRequest userRequest) {
    	log.info("usersPost {}", userRequest.getMail());
        return new ResponseEntity<UserResponse>(userAdapter.createUser(userRequest), HttpStatus.CREATED);
    }

	
	public ResponseEntity<UserResponse> usersUserIDGet(UUID userID) {
		log.info("usersUserIDGet {}", userID);
		return new ResponseEntity<UserResponse>(userAdapter.getUserById(userID), HttpStatus.OK);
	}

    
}
