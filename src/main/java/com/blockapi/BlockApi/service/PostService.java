package com.blockapi.BlockApi.service;

import com.blockapi.BlockApi.payloads.PostDto;
import com.blockapi.BlockApi.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create Post
    PostDto createPost (PostDto postDto,Integer userId,Integer categoryId);

    //update Post
    PostDto updatePost(PostDto postDto ,Integer postId );

    //Delete Post
    void deletePost (Integer postId);

    //Get All Posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //Get single Post
    PostDto getPostById(Integer postId);

    //Get All Posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //Get All Posts by user
    List<PostDto> getPostsByUser(Integer userId);

    // Search Post by keyword

    List<PostDto> serachPost(String keyword);


}
