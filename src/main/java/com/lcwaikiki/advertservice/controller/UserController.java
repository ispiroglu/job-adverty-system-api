package com.lcwaikiki.advertservice.controller;


import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) throws UserNotFoundException {
        return userRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public User create(@Valid @RequestBody User user) {
        return userRepository.create(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{id}")
    public void update(@Valid @RequestBody User user, @PathVariable int id) throws UserNotFoundException {
        userRepository.update(user, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userRepository.delete(id);
    }

}
