package com.vibehub.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse{
    String message;
    boolean status;
}
