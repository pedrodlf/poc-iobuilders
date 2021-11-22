package com.pedrodlf.iobuilders.bank.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.pedrodlf.iobuilders.model.UserRequest;
import com.pedrodlf.iobuilders.model.UserResponse;


@RunWith(MockitoJUnitRunner.class)
public class UserAdapterTest {

    private UserAdapter sut;
    private UserService userService;

    @Before
    public void setUp(){
        userService =  mock(UserService.class);
        sut = new UserAdapter(userService);
    }


    @Test
    public void whenCreateUserIsInvokedThenCreateUserServiceIsInvoked(){
        UserRequest userRequest = new UserRequest();
        UserResponse expectedUserResponse = new UserResponse();
        expectedUserResponse.setUserID(UUID.randomUUID());
        expectedUserResponse.setUri("/users/" + expectedUserResponse.getUserID());
        when(userService.createUser(userRequest)).thenReturn(expectedUserResponse);

        UserResponse response = sut.createUser(userRequest);

        assertEquals(response.getUri(),expectedUserResponse.getUri());
        assertEquals(response.getUserID(),expectedUserResponse.getUserID());
    }

    @Test
    public void GivenAUUIDWhenFindByIdIsInvokedThenResponseIsOK(){
        UUID userId = UUID.randomUUID();
        UserResponse expectedUserData = new UserResponse();
        expectedUserData.setName("testName");
        expectedUserData.setMail("testMail");
        expectedUserData.setSurname("testSurname");
        expectedUserData.setUserID(userId);

        when(userService.getUserById(userId)).thenReturn(expectedUserData);
        UserResponse response = sut.getUserById(userId);

        assertNotNull(response);
        assertEquals(response.getUserID(),expectedUserData.getUserID());
        assertEquals(response.getName(),expectedUserData.getName());
    }

}