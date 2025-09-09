package com.example.tanmay.library_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tanmay.library_management.Config.JwtUtil;
import com.example.tanmay.library_management.Model.UserModel;
import com.example.tanmay.library_management.Service.UserAuthService;

@RestController
public class UsersLoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private JwtUtil jwtUtil;
    
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
