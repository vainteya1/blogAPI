package com.blockapi.BlockApi.java8Features;

import com.blockapi.BlockApi.model.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class forEachMethod {

    public static void main(String[] arg){
        int i=0;
        List <Integer> makeList=new ArrayList<Integer>();
        for (i=0;i<=10;i++){
            makeList.add(i);
                        }
        makeList.forEach(li-> System.out.println("list  "+li));

        }

        
    }

