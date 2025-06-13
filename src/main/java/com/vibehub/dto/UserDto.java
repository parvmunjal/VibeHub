package com.vibehub.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private String id;
    private String name;
    private String userName;
    private String gender;
    private String bio;
    private String email;
    private String phNo;
    private String profilePicUrl;
    private List<String> posts=new ArrayList<>();
    private List<String> followers=new ArrayList<>();
    private List<String> followings=new ArrayList<>();
    private List<String> stories=new ArrayList<>();
}
