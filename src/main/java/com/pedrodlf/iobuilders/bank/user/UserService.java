package com.pedrodlf.iobuilders.bank.user;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pedrodlf.iobuilders.bank.user.adapter.rest.UserRequestToUserConverter;
import com.pedrodlf.iobuilders.bank.user.adapter.rest.UserToUserResponseConverter;
import com.pedrodlf.iobuilders.bank.user.exception.UserNotFoundException;
import com.pedrodlf.iobuilders.model.UserRequest;
import com.pedrodlf.iobuilders.model.UserResponse;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserRequestToUserConverter userRequestToUserConverter;
    private UserToUserResponseConverter userToUserResponseConverter;
    

    public UserService(UserRepository userRepository,
                       UserRequestToUserConverter userRequestToUserConverter,
                       UserToUserResponseConverter userToUserResponseConverter){

        this.userRepository = userRepository;
        this.userRequestToUserConverter = userRequestToUserConverter;
        this.userToUserResponseConverter = userToUserResponseConverter;
    }

    public UserResponse createUser(UserRequest userRequest){
        User user = userRequestToUserConverter.convert(userRequest);
        final User savedUser = userRepository.save(user);
        return userToUserResponseConverter.convert(savedUser);
    }

    public UserResponse getUserById(UUID userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userToUserResponseConverter.convert(user);
    }
    
    public boolean isPresent(UUID userId) {
    	return userRepository.findById(userId).isPresent();
    }


}
