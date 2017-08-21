package com.jennilyn.controllers;

import com.jennilyn.models.User;
import com.jennilyn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiUserController {

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/api/users")
    public Iterable<User> userApiList(){ return userRepo.findAll(); }

    @RequestMapping("/api/users/{userId}")
    public User userApiById(@PathVariable("userId") long userId){
        return userRepo.findOne(userId);
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User addUser(@RequestBody User user){
        userRepo.save(user);
        return userRepo.findByUsername(user.getUsername());
    }

    @RequestMapping(value = "/api/users/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User updateUser(@PathVariable("userId") User user,
                           @RequestBody User userUpdate){
        user.setUsername(userUpdate.getUsername());
        user.setAddress(userUpdate.getAddress());
        user.setCity(userUpdate.getCity());
        user.setZipCode(userUpdate.getZipCode());
        user.setPhone(userUpdate.getPhone());
        userRepo.save(user);
        return user;
    }

}
