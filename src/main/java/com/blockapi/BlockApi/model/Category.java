package com.blockapi.BlockApi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cat_Id;
    private String cat_Name;
    private String cat_description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();
}
