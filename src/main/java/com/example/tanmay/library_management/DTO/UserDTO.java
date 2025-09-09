package com.example.tanmay.library_management.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String role;
    private boolean isStudent;
    private int studentId;
    private String name;
}
