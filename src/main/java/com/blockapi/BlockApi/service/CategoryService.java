package com.blockapi.BlockApi.service;

import com.blockapi.BlockApi.model.Category;
import com.blockapi.BlockApi.payloads.CategoeyDto;

import java.util.List;

public interface CategoryService {
    CategoeyDto createCat (CategoeyDto categoeyDto) ;
    CategoeyDto updateCat(CategoeyDto categoeyDto,Integer catId);
    CategoeyDto getCatById(Integer catId);
    List<CategoeyDto> getAllCat();

    void deleteCat(Integer catId);
}
