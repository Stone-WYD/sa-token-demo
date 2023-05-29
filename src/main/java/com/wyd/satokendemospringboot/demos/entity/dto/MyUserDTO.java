package com.wyd.satokendemospringboot.demos.entity.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class MyUserDTO implements Serializable {

    private Long id;

    private String userName;

    private Integer age;
}
