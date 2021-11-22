package com.pedrodlf.iobuilders.bank.user.adapter.rest;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.pedrodlf.iobuilders.bank.user.UserService;
import com.pedrodlf.iobuilders.model.UserRequest;
import com.pedrodlf.iobuilders.model.UserResponse;

@Component
public class UserAdapter {

    private UserService userService;

    public UserAdapter(UserService userService){
        this.userService = userService;
    }

    public UserResponse createUser(UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    public UserResponse getUserById(UUID userId){
        return userService.getUserById(userId);
    }
    
    public boolean isPresent(UUID userId) {
    	return userService.isPresent(userId);
    }
}
