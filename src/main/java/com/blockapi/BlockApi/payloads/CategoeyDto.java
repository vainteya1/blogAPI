package com.blockapi.BlockApi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CategoeyDto {

    private Integer cat_Id;
    @NotBlank
    private String cat_Name;
    @NotBlank
    private String cat_description;
}
