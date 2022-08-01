package com.lcwaikiki.advertservice.controller;


import com.lcwaikiki.advertservice.dto.model.user.UserCredentialDto;
import com.lcwaikiki.advertservice.dto.request.user.CreateUserRequest;
import com.lcwaikiki.advertservice.dto.request.user.UpdateUserCvRequest;
import com.lcwaikiki.advertservice.dto.request.user.UpdateUserPhotoRequest;
import com.lcwaikiki.advertservice.dto.request.user.UpdateUserRequest;
import com.lcwaikiki.advertservice.dto.response.user.UserApplicationsResponse;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  @PostMapping("/{id}/photo")
  public void upload(UpdateUserPhotoRequest request, @PathVariable Long id)
      throws UserNotFoundException, IOException {
    userService.updateUserProfilePicture(request.getFile(), id);
  }

  @GetMapping("/{id}/photo")
  public ResponseEntity<byte[]> getUserProfilePhoto(@PathVariable Long id)
      throws UserNotFoundException {
    return ResponseEntity.ok(userService.getUserProfilePhoto(id));
  }

  @PostMapping("/{id}/cv")
  public void upload(UpdateUserCvRequest request, @PathVariable Long id)
      throws UserNotFoundException, IOException {
    userService.updateUserCv(request.getFile(), id);
  }

  @GetMapping("/{id}/cv")
  public ResponseEntity<byte[]> getUserCv(@PathVariable Long id) throws UserNotFoundException {
    return ResponseEntity.ok().header(id + "_cv")
        .contentType(MediaType.APPLICATION_PDF).body(userService.getUserCv(id));
  }

  @GetMapping("/{id}/applications")
  public ResponseEntity<UserApplicationsResponse> getUserApplications(@PathVariable Long id)
      throws UserNotFoundException {
    return ResponseEntity.ok(userService.getAppliedAdverts(id));
  }
}
