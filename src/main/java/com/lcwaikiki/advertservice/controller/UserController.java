package com.lcwaikiki.advertservice.controller;


import com.lcwaikiki.advertservice.dto.CreateUserRequest;
import com.lcwaikiki.advertservice.dto.UpdateUserRequest;
import com.lcwaikiki.advertservice.dto.UserCredentialDto;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

  private final UserService userService;


  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable long id) throws UserNotFoundException {
    return ResponseEntity.ok().body(userService.findById(id));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PutMapping
  public ResponseEntity<UserCredentialDto> createUser(
      @RequestBody CreateUserRequest createUserRequest) {
    return ResponseEntity.ok(userService.createUser(createUserRequest));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT) // Is this approach true?
  @PostMapping(value = "/{id}")
  public void update(
      @Valid @RequestBody UpdateUserRequest updateUserRequest, @PathVariable Long id)
      throws UserNotFoundException {
    userService.updateUser(id, updateUserRequest);
//    return ResponseEntity.ok(userService.updateUser(id, updateUserRequest));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable long id) throws UserNotFoundException {
    userService.deleteUser(id);
  }

}
