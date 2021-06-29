package com.example.springtest.service;

import com.example.springtest.dto.UserDto;
import com.example.springtest.exceptions.ValidationException;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto) throws ValidationException;
    void deleteUser(Integer id);
    UserDto findByName(String login);
    List<UserDto> findAll();
}
