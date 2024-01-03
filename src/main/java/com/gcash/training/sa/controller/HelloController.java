package com.gcash.training.sa.controller;

import com.gcash.training.sa.model.User;
import com.gcash.training.sa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author johnmichael.gerona
 * @created 12/14/23
 */

@RestController
@RequestMapping("/users")
public class HelloController {

    private UserService userService;

    @Autowired
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = { "", "/" })
    List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = {  "/{id}", "/{id}/" })
    User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping(value = {   "", "/"})
    Integer addUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PutMapping(value = {  "/{id}", "/{id}/" })
    Integer editUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(value = {  "/{id}", "/{id}/" })
    Integer deleteUser(@PathVariable String id) {
        return userService.removeUser(id);
    }
}
