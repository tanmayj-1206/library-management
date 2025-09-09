package com.example.tanmay.library_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.tanmay.library_management.DTO.UserDTO;
import com.example.tanmay.library_management.Model.UserModel;
import com.example.tanmay.library_management.Service.UserService;
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
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("addadminuser")
    public String addUser(@RequestBody UserModel user) {
        System.out.println("in User class");
        userService.addUser(user);
        return "success";
    }
    
}
