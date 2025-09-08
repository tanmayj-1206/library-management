package com.example.tanmay.library_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.example.tanmay.library_management.Config.JwtUtil;
import com.example.tanmay.library_management.Model.UserModel;
import com.example.tanmay.library_management.Service.UserAuthService;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("getusers")
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("addadminuser")
    public String addUser(@RequestBody UserModel user) {
        System.out.println("in User class");
        userService.addUser(user);
        return "success";
    }
    
    @PostMapping("login")
    public String login(@RequestBody UserModel user) {
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            return "Login Failed";
        }
        UserDetails loggedInUser = userAuthService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(loggedInUser);
        return token;
    }
    
}
