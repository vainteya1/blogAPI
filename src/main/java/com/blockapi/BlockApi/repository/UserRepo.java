package com.blockapi.BlockApi.repository;

import com.blockapi.BlockApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
