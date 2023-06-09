package com.blockapi.BlockApi.service.impl;

import com.blockapi.BlockApi.exception.ResourceNotFoundException;
import com.blockapi.BlockApi.model.Category;
import com.blockapi.BlockApi.model.Post;
import com.blockapi.BlockApi.model.User;
import com.blockapi.BlockApi.payloads.PostDto;
import com.blockapi.BlockApi.payloads.PostResponse;
import com.blockapi.BlockApi.repository.CategoryRepo;
import com.blockapi.BlockApi.repository.PostRepo;
import com.blockapi.BlockApi.repository.UserRepo;
import com.blockapi.BlockApi.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    Logger log= LogManager.getLogger();
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {


            log.info("new post Create servviceimpl......start");
            User user=this.userRepo.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
            Category category=this.categoryRepo.findById(categoryId)
                    .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
           Post post= this.modelMapper.map(postDto,Post.class);
            post.setImageName("demo.png");
            post.setPostDate(new Date());
            post.setUser(user);
            post.setCategory(category);
           Post newPost=this.postRepo.save(post);
            log.info("Post Created servImpl>>>>>end");


        return this.modelMapper.map(newPost,PostDto.class);
    }




    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost=this.postRepo.save(post);
        PostDto postUpdated=this.modelMapper.map(updatedPost,PostDto.class);

        return postUpdated;
    }

    @Override
    public void deletePost(Integer postId) {
        try {
            log.info("at delete Post postsrvimpl>>>>>>>>entr");
            Post post=this.postRepo.findById(postId)
                    .orElseThrow(()-> new  ResourceNotFoundException("PostId","postId",postId));
            this.postRepo.delete(post);
            log.info("At delete Post srvimpl>>>>>. enter");
        }catch (Exception ex){
            log.error("Error Occued At delete post" +ex);
        }

    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        log.info("At get All Post postSrvimpl>>>>>>>enter");
        Sort sort=null;
        if (sortDir.equalsIgnoreCase("sortDir")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> pagePost=this.postRepo.findAll(p);
        List<Post> post=pagePost.getContent();

        List<PostDto> postDtos=post.stream().map((posts)-> this.modelMapper.map(posts,PostDto.class))
                                .collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        log.info("At get all posts >>>>>>>>> End");
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post =new Post();
        try {
            log.info("At the start of get posts by id>>>>>>");
             post=this.postRepo.findById(postId)
                    .orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
             log.info("At exit of get Posts by Id");
        }catch (Exception ex){
            log.error("Exception Found at get post by postID");
        }
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        log.info("At get Posts by Category.....Start");
        Category cat=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
       List<Post> posts= this.postRepo.findByCategory(cat);
      List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class))
                                .collect(Collectors.toList());
      log.info("at the exit in get posts by category ........");
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        log.info("In get Post by user in postsrvimpl.....start");
        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        List<Post> posts=this.postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class))
                                .collect(Collectors.toList());
        log.info("at end of get post by user ......");
        return postDtos;
    }

    @Override
    public List<PostDto> serachPost(String keyword) {
        log.info("In search Post serviceImpl.....start");
                List<Post> posts=this.postRepo.findByTitleContaining(keyword);
                List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper
                .map(post,PostDto.class)).collect(Collectors.toList());
        log.info("In Search Post servimpl.......end");
        return postDtos;
    }
}
