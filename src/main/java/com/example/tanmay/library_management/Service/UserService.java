package com.example.tanmay.library_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tanmay.library_management.Model.UserModel;
import com.example.tanmay.library_management.Repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<UserModel> getUsers() {
        return repo.findAll();
    }

    public void addUser(UserModel user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }
}
