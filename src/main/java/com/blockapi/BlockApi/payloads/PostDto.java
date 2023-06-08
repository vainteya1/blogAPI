package com.blockapi.BlockApi.payloads;

import com.blockapi.BlockApi.model.Category;
import com.blockapi.BlockApi.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private  Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date postDate;
    private CategoeyDto category;
    private UserDto user;
}
