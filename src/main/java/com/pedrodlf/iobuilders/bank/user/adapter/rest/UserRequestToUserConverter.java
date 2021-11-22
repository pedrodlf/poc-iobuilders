package com.pedrodlf.iobuilders.bank.user.adapter.rest;

import com.pedrodlf.iobuilders.bank.user.User;
import com.pedrodlf.iobuilders.model.UserRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRequestToUserConverter implements Converter<UserRequest, User>
{
    @Override
    public User convert(UserRequest request) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setMail(request.getMail());
        user.setPass(request.getPass());
        return user;
    }
}
