package com.example.contactmanager.service;

import com.example.contactmanager.config.CustomUserDetails;
import com.example.contactmanager.entity.User;
import com.example.contactmanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //fetching user from database
        User user=userRepository.getUserByUserName(username);

        if (user==null){
            throw new UsernameNotFoundException("Could not found user @@");
        }
        CustomUserDetails details=new CustomUserDetails(user);
        return details;
    }
}