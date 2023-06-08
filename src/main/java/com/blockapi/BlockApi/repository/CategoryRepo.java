package com.blockapi.BlockApi.repository;

import com.blockapi.BlockApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
