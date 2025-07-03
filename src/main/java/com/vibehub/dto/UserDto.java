package com.vibehub.dto;

import com.vibehub.models.Role;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String userName;
    private String password;
    private String gender;
    private String bio;
    private String email;
    private String phNo;
    private String profilePicUrl;
    private Role role;
}
