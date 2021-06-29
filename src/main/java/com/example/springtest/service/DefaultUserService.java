package com.example.springtest.service;

import com.example.springtest.converters.UserConverter;
import com.example.springtest.dto.UserDto;
import com.example.springtest.entity.User;
import com.example.springtest.exceptions.ValidationException;
import com.example.springtest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository usersRepository;
    private final UserConverter usersConverter;

    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException {
        validateUserDto(userDto);
        User savedUser = usersRepository.save(usersConverter.fromUserDtoToUser(userDto));
        return usersConverter.fromUserToUserDto(savedUser);
    }

    @Override
    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public UserDto findByName(String name) {
        User user = usersRepository.findByName(name);
        if (user != null) {
            return usersConverter.fromUserToUserDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return usersRepository.findAll().stream()
                .map(user -> usersConverter.fromUserToUserDto(user))
                .collect(Collectors.toList());
    }

    private void validateUserDto(UserDto userDto) throws ValidationException {
        if (isNull(userDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(userDto.getName()) || userDto.getName().isEmpty()) {
            throw new ValidationException("Name is empty");
        }
    }
}
