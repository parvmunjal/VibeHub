package com.vibehub.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
