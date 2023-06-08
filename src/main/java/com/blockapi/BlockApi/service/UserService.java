package com.blockapi.BlockApi.service;

import com.blockapi.BlockApi.model.User;
import com.blockapi.BlockApi.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
