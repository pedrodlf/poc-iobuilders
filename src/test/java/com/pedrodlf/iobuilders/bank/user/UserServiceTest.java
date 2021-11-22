package com.pedrodlf.iobuilders.bank.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.pedrodlf.iobuilders.bank.user.adapter.rest.UserRequestToUserConverter;
import com.pedrodlf.iobuilders.bank.user.adapter.rest.UserToUserResponseConverter;
import com.pedrodlf.iobuilders.model.UserRequest;
import com.pedrodlf.iobuilders.model.UserResponse;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService sut;
    private UserRepository userRepository;
    private UserRequestToUserConverter userRequestToUserConverter;
    private UserToUserResponseConverter userToUserResponseConverter;


    @Before
    public void setUp(){
        userRepository = mock(UserRepository.class);
        userRequestToUserConverter = mock(UserRequestToUserConverter.class);
        userToUserResponseConverter = mock(UserToUserResponseConverter.class);
        sut = new UserService(userRepository, userRequestToUserConverter,userToUserResponseConverter);
    }

    @Test
    public void whenUserIsCreatedThenUserResponseIsReturned(){
        UUID userId = UUID.randomUUID();
        UserRequest userRequest = new UserRequest();
        User expectedUser = new User();
        UserResponse expectedUserResponse = new UserResponse();
        expectedUserResponse.setUserID(userId);

        when(userRequestToUserConverter.convert(userRequest)).thenReturn(expectedUser);
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
        when(userToUserResponseConverter.convert(expectedUser)).thenReturn(expectedUserResponse);
        sut.createUser(userRequest);

        verify(userRequestToUserConverter,times(1)).convert(userRequest);
        verify(userToUserResponseConverter,times(1)).convert(expectedUser);
        verify(userRepository,times(1)).save(expectedUser);
    }

    @Test
    public void whenFindByIdMethodIsInvokedThenUserDataIsReceived(){
        UUID userId = UUID.randomUUID();
        User expectedUser = new User();
        UserResponse expectedUserData = new UserResponse();
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));
        when(userToUserResponseConverter.convert(expectedUser)).thenReturn(expectedUserData);
        sut.getUserById(userId);

        verify(userRepository,times(1)).findById(userId);
        verify(userToUserResponseConverter,times(1)).convert(expectedUser);
    }
}