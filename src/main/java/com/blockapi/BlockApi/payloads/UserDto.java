package com.blockapi.BlockApi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min=4,message = "Username Must Be 4 Charecters..")
    private String name;
    @Email(message = "Email is Not Valid")
    private String emailId;
    @NotEmpty
    @Size(min=4,max=10,message = "Password must be min 4 charecters and max 10 ")
    private String password;
    @NotEmpty
    private String about;
}
