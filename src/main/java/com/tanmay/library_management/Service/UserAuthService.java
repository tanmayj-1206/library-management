package com.tanmay.library_management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tanmay.library_management.Model.UserModel;
import com.tanmay.library_management.Repo.UserRepo;
@Component
public class UserAuthService implements UserDetailsService {
    @Autowired
    private UserRepo repo;
    private UserModel user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found : 404"));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
                .build();
    }

}
