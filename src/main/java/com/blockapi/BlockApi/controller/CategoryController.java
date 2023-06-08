package com.blockapi.BlockApi.controller;

import com.blockapi.BlockApi.payloads.ApiResponse;
import com.blockapi.BlockApi.payloads.CategoeyDto;
import com.blockapi.BlockApi.service.CategoryService;
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
    //POST - Create Category
    @PostMapping("/")
    public ResponseEntity <CategoeyDto> createCategory(@Valid @RequestBody CategoeyDto categoeyDto){
        CategoeyDto createCateDto=this.categoryService.createCat(categoeyDto);
        return new ResponseEntity<CategoeyDto>(createCateDto, HttpStatus.CREATED);
    }

    //PUT- Update Category
    @PutMapping("/{catId}")
    public ResponseEntity<CategoeyDto>updateCategory(@Valid @RequestBody CategoeyDto cat_Id ,@PathVariable Integer catId){
        CategoeyDto updateCatDto=this.categoryService.updateCat(cat_Id,catId);
    return new ResponseEntity<CategoeyDto>(updateCatDto,HttpStatus.OK);
    }

    //DELETE - Category
    @DeleteMapping("/{catId}")
    public ResponseEntity <ApiResponse> deleteCat(@PathVariable Integer catId){
        this.categoryService.deleteCat(catId);

        return new ResponseEntity<ApiResponse>(new ApiResponse ("Category is deleted Successfully",true), HttpStatus.OK);
    }

    //GET - SingleCategory
    @GetMapping("/{catId}")
    public ResponseEntity<CategoeyDto> getSingleCat( @PathVariable Integer catId){
        return ResponseEntity.ok(this.categoryService.getCatById(catId));
    }

    //Get- AllCategory
    @GetMapping("/")
    public ResponseEntity <List<CategoeyDto>> getAllCat(){
       List<CategoeyDto> categories= this.categoryService.getAllCat();
        return ResponseEntity.ok(categories);
    }
}
