package com.blockapi.BlockApi.controller;

import com.blockapi.BlockApi.payloads.ApiResponse;
import com.blockapi.BlockApi.payloads.PostDto;
import com.blockapi.BlockApi.payloads.PostResponse;
import com.blockapi.BlockApi.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger log= LogManager.getLogger(PostController.class);
    // Create Post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
            ){
          log.info("At controller Create Post.....entr");
         PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
         log.info("At controller Post Created ... exit");
        return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
    }

    // Get Posts By User
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        log.info("Get Posts By User....entr");
        List<PostDto> posts= this.postService.getPostsByUser(userId);

        log.info("Got all the Posts by user....exit");
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //Get Posts By Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
            @PathVariable Integer categoryId){
            log.info("get all Posts by Category.......entr");
        List<PostDto> posts1= this.postService.getPostsByCategory(categoryId);
            log.info("got all the Posts by Category....exit");
        return new ResponseEntity <List<PostDto>> (posts1,HttpStatus.OK);
    }
    
    //Get Single Post
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        log.info("Get Single Post By post Id.....entr");
        PostDto postDto=this.postService.getPostById(postId);
        log.info("Got All Posts by Id....exit");
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
        log.info("Get All Posts.....entr");
        PostResponse postResponse=this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        log.info("got All the Posts......exit");
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    //Delete Post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        log.info("Delete the Post...entr");
        this.postService.deletePost(postId);
        log.info("Post Deleted.....exit");
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully ",true),HttpStatus.OK);
    }

    //Updated Post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        log.info("Update Post ......entr");
        PostDto updatePost=this.postService.updatePost(postDto,postId);
        log.info("Post Updated........exit");
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    //Search
    @GetMapping("/posts/search/{keyword}")
public ResponseEntity <List<PostDto>> searchPostByTitle(
        @PathVariable("keyword")  String keyword){
            log.info("serach Post ..... entr");
            List<PostDto> result=this.postService.serachPost(keyword);
            log.info("Posts Searched by keywords....exit");
            return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
}
}
