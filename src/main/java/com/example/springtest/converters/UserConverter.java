package com.example.springtest.converters;

import com.example.springtest.dto.UserDto;
import com.example.springtest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .ID(userDto.getID())
                .name(userDto.getName())
                .description(userDto.getDescription())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .ID(user.getID())
                .name(user.getName())
                .description(user.getDescription())
                .password(user.getPassword())
                .build();
    }
}
