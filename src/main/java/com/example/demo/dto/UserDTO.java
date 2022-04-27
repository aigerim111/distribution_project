package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    private String nameKZ;
    private String nameRu;
    @NotEmpty
    private String phone;
}
