package com.Yadeedya.Yadeedya_social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Yadeedya.Yadeedya_social_media.exceptions.UserException;
import com.Yadeedya.Yadeedya_social_media.models.User;
import com.Yadeedya.Yadeedya_social_media.repository.UserRepository;
import com.Yadeedya.Yadeedya_social_media.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userid}")
    public User getUserById(@PathVariable("userid") Integer id) throws UserException {
        return userService.findUserById(id);
    }

    @PutMapping
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.updateUser(user, reqUser.getId());
    }

    @PutMapping("/follow/{userid2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userid2) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.followUser(reqUser.getId(), userid2);
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.searchUser(query);
    }

    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        return userService.findUserByJwt(jwt);
    }
}
