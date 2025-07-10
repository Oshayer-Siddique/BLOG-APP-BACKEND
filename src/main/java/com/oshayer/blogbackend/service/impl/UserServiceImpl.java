package com.oshayer.blogbackend.service.impl;

import com.oshayer.blogbackend.dto.UserDto;
import com.oshayer.blogbackend.entities.User;
import com.oshayer.blogbackend.exception.ResourceNotFoundException;
import com.oshayer.blogbackend.repository.UserRepo;
import com.oshayer.blogbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // Update user entity with new data from DTO
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setRole(userDto.getRole());

        User updatedUser = this.userRepo.save(user);

        return this.userToDto(updatedUser);
    }
    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());

        return userDtos;

    }
    @Override
    public void deleteUserById(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);


    }


    private User dtoToUser(UserDto userDto){

        User user = this.modelMapper.map(userDto, User.class);




//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setRole(userDto.getRole());
//        user.setAbout(userDto.getAbout());
        return user;


    }


    public UserDto userToDto(User user){

        UserDto userDto = this.modelMapper.map(user, UserDto.class);

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setRole(user.getRole());
//        userDto.setAbout(user.getAbout());
        return userDto;

    }


}
