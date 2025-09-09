package com.tanmay.library_management.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.library_management.DTO.APIResponseWrapper;
import com.tanmay.library_management.Model.UserModel;
import com.tanmay.library_management.Service.UserAuthService;
import com.tanmay.library_management.Utility.JwtUtil;

@RestController
public class UsersLoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthService userAuthService;

    
    @PostMapping("api/login")
    public ResponseEntity<APIResponseWrapper<Map<String, Object>>> login(@RequestBody UserModel user) {
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(APIResponseWrapper.error("Login Failed", null));
        }
        UserDetails loggedInUser = userAuthService.loadUserByUsername(user.getUsername());
        String token = JwtUtil.generateToken(loggedInUser);
        Map<String, Object> tokenMap = Map.of("token", token);
        return ResponseEntity.ok(APIResponseWrapper.success("Login Successful", tokenMap));
    }
}
