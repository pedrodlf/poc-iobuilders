package com.pedrodlf.iobuilders.bank.user.adapter.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedrodlf.iobuilders.bank.user.UserAdapter;
import com.pedrodlf.iobuilders.model.UserRequest;
import com.pedrodlf.iobuilders.model.UserResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(UserControllerImpl.class)
public class UserControllerImplTest {

    @MockBean
    private UserAdapter userAdapter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void createUserSuccess() throws Exception{
        UserRequest userRequest = new UserRequest();
        userRequest.setName("testName");
        userRequest.setSurname("testSurname");
        userRequest.setMail("testMail");
        userRequest.setPass("testPass");
        UUID userId = UUID.randomUUID();
        UserResponse userResponse = new UserResponse();
        userResponse.setUserID(userId);
        userResponse.setUri("/users/" + userId);
        when(userAdapter.createUser(any())).thenReturn(userResponse);

        MvcResult result = mockMvc.perform(post("/users")
                .content(this.objectMapper.writeValueAsBytes(userRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        assertEquals(201, result.getResponse().getStatus());
        assertNotNull(result.getResponse().getContentAsString());
    }

}