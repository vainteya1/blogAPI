package com.blockapi.BlockApi.service.impl;

import com.blockapi.BlockApi.exception.ResourceNotFoundException;
import com.blockapi.BlockApi.model.Category;
import com.blockapi.BlockApi.payloads.CategoeyDto;
import com.blockapi.BlockApi.repository.CategoryRepo;
import com.blockapi.BlockApi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;



    private CategoeyDto categoryToDto(Category category){
        CategoeyDto categoryDto=this.modelMapper.map(category ,CategoeyDto.class );

        return categoryDto;
    }

    private Category dtoToCategory(CategoeyDto categoryDto){
        Category category=this.modelMapper.map(categoryDto,Category.class);
        return category;
    }

    @Override
    public CategoeyDto createCat(CategoeyDto categoryDto) {
        Category cat=this.modelMapper.map(categoryDto,Category.class);
        Category addCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(addCat,CategoeyDto.class);
    }

    @Override
    public CategoeyDto updateCat(CategoeyDto categoryDto, Integer catId) {
        Category cat=this.categoryRepo.findById(catId)
                .orElseThrow(()-> new ResourceNotFoundException("category","catId",catId));
        cat.setCat_Name(categoryDto.getCat_Name());
        cat.setCat_description(categoryDto.getCat_description());
        Category updatedCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCat,CategoeyDto.class) ;
    }

    @Override
    public CategoeyDto getCatById(Integer catId) {
        Category cat=this.categoryRepo.findById(catId)
                .orElseThrow(()-> new ResourceNotFoundException("category","catId",catId));
        CategoeyDto categoeyDto1=this.categoryToDto(cat);
        return categoeyDto1;
    }

    @Override
    public List<CategoeyDto> getAllCat() {
        List<Category> category=this.categoryRepo.findAll();
        List<CategoeyDto> categoryList=category.stream().map(catAll-> this.categoryToDto(catAll)).collect(Collectors.toList());
        return categoryList;
    }

    @Override
    public void deleteCat(Integer catId) {
        Category category=this.categoryRepo.findById(catId)
                .orElseThrow(()-> new ResourceNotFoundException("category","catId" ,catId));

        this.categoryRepo.delete(category);
    }


}
