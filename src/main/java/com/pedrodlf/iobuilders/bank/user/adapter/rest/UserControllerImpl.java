package com.pedrodlf.iobuilders.bank.user.adapter.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrodlf.iobuilders.api.UsersApi;
import com.pedrodlf.iobuilders.bank.user.UserAdapter;
import com.pedrodlf.iobuilders.model.UserRequest;
import com.pedrodlf.iobuilders.model.UserResponse;

@RestController
@RequestMapping("/v1")
public class UserControllerImpl implements UsersApi{

    private UserAdapter userAdapter;

    public UserControllerImpl(UserAdapter userAdapter){
        this.userAdapter = userAdapter;
    }

    public ResponseEntity<UserResponse> usersPost(UserRequest userRequest) {
        return new ResponseEntity<UserResponse>(userAdapter.createUser(userRequest), HttpStatus.CREATED);
    }

	
	public ResponseEntity<UserResponse> usersUserIDGet(UUID userID) {
		
		return new ResponseEntity<UserResponse>(userAdapter.getUserById(userID), HttpStatus.OK);
	}

    
}
