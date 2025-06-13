package com.vibehub.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String userName;
    private String gender;
    private String phNo;
    private String email;
    private String password;
    private String bio;
    private String profilePicUrl;
    private List<String> followers=new ArrayList<>();
    private List<String> followings=new ArrayList<>();
    private List<String> stories=new ArrayList<>();
    private List<String> posts=new ArrayList<>();
}
