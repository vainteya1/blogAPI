package com.blockapi.BlockApi.repository;

import com.blockapi.BlockApi.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository< Post,Integer > {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
