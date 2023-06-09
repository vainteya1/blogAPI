package com.blockapi.BlockApi.controller;

import com.blockapi.BlockApi.payloads.ApiResponse;
import com.blockapi.BlockApi.payloads.UserDto;
import com.blockapi.BlockApi.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    private static final Logger log= LogManager.getLogger(UserController.class);
    //POST - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        log.info("ENTER User ....");
        UserDto createdUserDto=this.userService.createser(userDto);
        log.info("User Created....");
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT - Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
        log.info("update user.....");
        UserDto updatedUser=this.userService.updateUser(userDto,userId);
        log.info("User Updated.....");
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE - Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
        log.info("delete User...");
        this.userService.deleteUser(userId);
        log.info("User Deleted........");
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }

    //GET - All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        log.info("getting All User Enter..");
        log.info("Got All users....Exit");
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //GET - Single User
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId){
        log.info("fetching single user.....");
        log.info("single User Fetched");
        return ResponseEntity.ok(this.userService.getUserById(userId));


    }

}
