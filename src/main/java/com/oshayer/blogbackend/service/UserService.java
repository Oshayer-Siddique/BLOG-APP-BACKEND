package com.oshayer.blogbackend.service;

import com.oshayer.blogbackend.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto userDto, Long userId);


    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();
    void deleteUserById(Long userId);





}
