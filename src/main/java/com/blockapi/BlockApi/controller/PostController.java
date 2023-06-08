package com.blockapi.BlockApi.controller;

import com.blockapi.BlockApi.payloads.ApiResponse;
import com.blockapi.BlockApi.payloads.PostDto;
import com.blockapi.BlockApi.payloads.PostResponse;
import com.blockapi.BlockApi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    PostService postService;
    // Create Post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
            ){


         PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
    }

    // Get Posts By User
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> posts= this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //Get Posts By Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
            @PathVariable Integer categoryId){

        List<PostDto> posts1= this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity <List<PostDto>> (posts1,HttpStatus.OK);
    }
    
    //Get Single Post
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto=this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    //Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "2") Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
            @RequestParam (value = "sortDir",defaultValue = "asc",required = false) String sortDir
            ){
        PostResponse postResponse=this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    //Delete Post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully ",true),HttpStatus.OK);
    }

    //Updated Post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatePost=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    //Search
    @GetMapping("/posts/search/{keyword}")
public ResponseEntity <List<PostDto>> searchPostByTitle(
        @PathVariable("keyword")  String keyword){
            List<PostDto> result=this.postService.serachPost(keyword);
            return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
}
}
