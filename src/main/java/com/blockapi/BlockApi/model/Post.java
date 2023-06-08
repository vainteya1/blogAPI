package com.blockapi.BlockApi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer postId;

    private String title;
    private String content;
    private String imageName;
    private Date postDate;
    @ManyToOne

    private Category category;
    @ManyToOne
    private User user;



}
