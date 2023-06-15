package com.blockapi.BlockApi.java8Features;

import java.util.stream.Stream;

public class StreamApi {

    public static void main(String[] arg){
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9);
        stream.forEach(p -> System.out.println("Hello>>>>" +p));
    }



}
