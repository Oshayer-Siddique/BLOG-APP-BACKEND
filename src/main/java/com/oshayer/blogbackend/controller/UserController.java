package com.oshayer.blogbackend.controller;

import com.oshayer.blogbackend.dto.UserDto;
import com.oshayer.blogbackend.entities.User;
import com.oshayer.blogbackend.payload.ApiResponse;
import com.oshayer.blogbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userService;


    //POST
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createuser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createuser, HttpStatus.CREATED);

    }


    //PUT
    @PutMapping("/update/{userid}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long userid) {
        UserDto updateuser = this.userService.updateUser(userDto, userid);
        return new ResponseEntity<>(updateuser, HttpStatus.OK);


    }


    //DELETE
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUserById(userId);

        ApiResponse apiResponse = new ApiResponse(
                "User deleted Successfully",
                HttpStatus.OK.toString()
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    //GET
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //GET Specific UserId
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable Long userId) {
        UserDto user = this.userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
