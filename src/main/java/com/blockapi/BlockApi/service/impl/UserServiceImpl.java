package com.blockapi.BlockApi.service.impl;

import com.blockapi.BlockApi.exception.ResourceNotFoundException;
import com.blockapi.BlockApi.model.User;
import com.blockapi.BlockApi.payloads.UserDto;
import com.blockapi.BlockApi.repository.UserRepo;
import com.blockapi.BlockApi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createser(UserDto userDto) {
        User user= this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new  ResourceNotFoundException("User","Id", userId));
        user.setName(userDto.getName());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser=this.userRepo.save(user);
        UserDto userDto1=this.userToDto(updatedUser);

       // user.setName(user.getName());
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));
        UserDto userDto2=this.userToDto(user);
        return userDto2;
    }

    @Override
    public List<UserDto>getAllUsers(){

        List<User> user = this.userRepo.findAll();
        List<UserDto> userDtoList = user.stream().map(useral -> this.userToDto(useral)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
            User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));

            this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        User user =this.modelMapper.map(userDto,User.class);
        //user.setId(userDto.getId());
        //user.setName(userDto.getName());
        //user.setAbout(userDto.getAbout());
        //user.setEmailId(userDto.getEmailId());
        //user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);

        //userDto.setId(user.getId());
        //userDto.setName(user.getName());
        //userDto.setAbout(user.getAbout());
        //userDto.setEmailId(user.getEmailId());
        //userDto.setPassword(user.getPassword());

        return userDto;
    }


}
