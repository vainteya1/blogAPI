package com.blockapi.BlockApi.service.impl;

import com.blockapi.BlockApi.controller.UserController;
import com.blockapi.BlockApi.exception.ResourceNotFoundException;
import com.blockapi.BlockApi.model.Category;
import com.blockapi.BlockApi.payloads.CategoeyDto;
import com.blockapi.BlockApi.repository.CategoryRepo;
import com.blockapi.BlockApi.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log= LogManager.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;



    private CategoeyDto categoryToDto(Category category){
        CategoeyDto categoryDto=new CategoeyDto();

        try {
            log.info("CategoryToDto.....enter");
             categoryDto=this.modelMapper.map(category ,CategoeyDto.class );
            log.info("exit from CategorytoDto.....");
        }catch (Exception ex) {
            log.error("exception Occurd at Service iml categorytoDto>>>>>>>"+ex);
        }


        return categoryDto;
    }

    private Category dtoToCategory(CategoeyDto categoryDto){
        Category category=new Category();
        try {
            log.info("dtoToCategory.....enter");
            category=this.modelMapper.map(categoryDto,Category.class);
            log.info("exit from dtoToCategory.....");
        }catch (Exception ex) {
            log.error("exception Occurd at Service iml dtoToCategory>>>>>>>"+ex);
        }
            return category;
        }

    @Override
    public CategoeyDto createCat(CategoeyDto categoryDto) {
        Category cat=new Category();
        Category addCat=new Category();
        try {
            log.info("createCategory.....enter");
            cat=this.modelMapper.map(categoryDto,Category.class);
             addCat=this.categoryRepo.save(cat);
            log.info("exit from createCategory.....");
        }catch (Exception ex) {
            log.error("exception Occurd at Service iml createCategory>>>>>>>"+ex);
        }

            return this.modelMapper.map(addCat,CategoeyDto.class);


    }

    @Override
    public CategoeyDto updateCat(CategoeyDto categoryDto, Integer catId) {
        Category updatedCat=new Category();
        try {
            log.info("updateCat.....enter");
            Category cat=this.categoryRepo.findById(catId)
                    .orElseThrow(()-> new ResourceNotFoundException("category","catId",catId));
            cat.setCat_Name(categoryDto.getCat_Name());
            cat.setCat_description(categoryDto.getCat_description());
             updatedCat=this.categoryRepo.save(cat);
            log.info("exit from updateCat.....");
        }catch (Exception ex){
            log.error("exception Occurd at Service iml updateCat>>>>>>>"+ex);
        }

        return this.modelMapper.map(updatedCat,CategoeyDto.class) ;
    }

    @Override
    public CategoeyDto getCatById(Integer catId) {
        CategoeyDto categoeyDto1=new CategoeyDto();
        try {
            log.info("getCatById.....enter");
                    Category cat=this.categoryRepo.findById(catId)
                    .orElseThrow(()-> new ResourceNotFoundException("category","catId",catId));
                     categoeyDto1=this.categoryToDto(cat);
            log.info("exit from getCatById.....");
        }catch (Exception ex){
            log.error("exception Occurd at Service iml getCatById>>>>>>>"+ex);
        }

        return categoeyDto1;
    }

    @Override
    public List<CategoeyDto> getAllCat() {

            log.info("get All Category.....enter");
            List<Category> category=this.categoryRepo.findAll();
             List<CategoeyDto>  categoryList=category.stream().map(catAll-> this.categoryToDto(catAll)).collect(Collectors.toList());
            log.info("get All category...... exit");

        return categoryList;
    }

    @Override
    public void deleteCat(Integer catId) {
        Category category;
        try {
            log.info("delete Cat.....enter");
                    category=this.categoryRepo.findById(catId)
                    .orElseThrow(()-> new ResourceNotFoundException("category","catId" ,catId));
            this.categoryRepo.delete(category);
            log.info("delete Category...... exit");
        }catch (Exception ex){
            log.error("exception occued at service Impl>>>>delete Cat");
        }



    }


}
