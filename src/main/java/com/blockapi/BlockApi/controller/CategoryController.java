package com.blockapi.BlockApi.controller;

import com.blockapi.BlockApi.payloads.ApiResponse;
import com.blockapi.BlockApi.payloads.CategoeyDto;
import com.blockapi.BlockApi.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;

    private static final Logger log= LogManager.getLogger(CategoryController.class);
    //POST - Create Category
    @PostMapping("/")
    public ResponseEntity <CategoeyDto> createCategory(@Valid @RequestBody CategoeyDto categoeyDto){
        log.info("Create Category.......entr");
        CategoeyDto createCateDto=this.categoryService.createCat(categoeyDto);
        log.info("Category Created .....exit");
        return new ResponseEntity<CategoeyDto>(createCateDto, HttpStatus.CREATED);
    }

    //PUT- Update Category
    @PutMapping("/{catId}")
    public ResponseEntity<CategoeyDto>updateCategory(@Valid @RequestBody CategoeyDto cat_Id ,@PathVariable Integer catId){
        log.info("Update Category.....entr");
        CategoeyDto updateCatDto=this.categoryService.updateCat(cat_Id,catId);
        log.info("Category Updated......exit");
    return new ResponseEntity<CategoeyDto>(updateCatDto,HttpStatus.OK);
    }

    //DELETE - Category
    @DeleteMapping("/{catId}")
    public ResponseEntity <ApiResponse> deleteCat(@PathVariable Integer catId){
        log.info("delete Category.......entr");
        this.categoryService.deleteCat(catId);
        log.info("Category Deleted......exit");
        return new ResponseEntity<ApiResponse>(new ApiResponse ("Category is deleted Successfully",true), HttpStatus.OK);
    }

    //GET - SingleCategory
    @GetMapping("/{catId}")
    public ResponseEntity<CategoeyDto> getSingleCat( @PathVariable Integer catId){
        log.info("get single category.....entr");
        log.info("got single category......exit");
        return ResponseEntity.ok(this.categoryService.getCatById(catId));
    }

    //Get- AllCategory
    @GetMapping("/")
    public ResponseEntity <List<CategoeyDto>> getAllCat(){
        log.info("get All Category......entr");
       List<CategoeyDto> categories= this.categoryService.getAllCat();
       log.info("Got All the list ........exit");
        return ResponseEntity.ok(categories);
    }
}
