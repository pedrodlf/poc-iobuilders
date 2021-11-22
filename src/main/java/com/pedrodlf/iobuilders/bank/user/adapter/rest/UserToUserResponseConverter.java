package com.pedrodlf.iobuilders.bank.user.adapter.rest;

import com.pedrodlf.iobuilders.bank.user.User;
import com.pedrodlf.iobuilders.model.UserResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseConverter implements Converter<User, UserResponse> {

    public static final String URI = "/users/";

    @Override
    public UserResponse convert(User user) {
       UserResponse userResponse = new UserResponse();
       userResponse.setUserID(user.getId());
       userResponse.setName(user.getName());
       userResponse.setSurname(user.getSurname());
       userResponse.setMail(user.getMail());
       userResponse.setUri(URI + user.getId());
       return userResponse;
    }
}
