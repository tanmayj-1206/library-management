package com.tanmay.library_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.library_management.DTO.APIResponseWrapper;
import com.tanmay.library_management.DTO.UserDTO;
import com.tanmay.library_management.Model.UserModel;
import com.tanmay.library_management.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getusers")
    public ResponseEntity<APIResponseWrapper<List<UserDTO>>> getUsers() {
        return ResponseEntity.ok(APIResponseWrapper.success("Users retrieved successfully", userService.getUsers()));
    }

    @PostMapping("addadminuser")
    public ResponseEntity<APIResponseWrapper<String>> addUser(@RequestBody UserModel user) {
        userService.addUser(user);
        return ResponseEntity.ok(APIResponseWrapper.success("User added successfully", null));
    }
    
}
