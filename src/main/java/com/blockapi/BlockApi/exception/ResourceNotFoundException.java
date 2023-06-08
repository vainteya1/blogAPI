package com.blockapi.BlockApi.exception;

public class ResourceNotFoundException extends RuntimeException{
    String resurceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resurceName,String fieldName,long fieldValue){
        super(String.format("%s not found with %s : %s", resurceName,fieldName,fieldValue));
        this.resurceName=resurceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
