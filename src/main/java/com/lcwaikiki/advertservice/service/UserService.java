package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.dto.CreateUserRequest;
import com.lcwaikiki.advertservice.dto.UpdateUserRequest;
import com.lcwaikiki.advertservice.dto.UserCredentialDto;
import com.lcwaikiki.advertservice.dto.converter.UserDtoConverter;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.ApplicationDetail;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserDtoConverter userDtoConverter;

  public UserService(UserRepository userRepository,
      UserDtoConverter userDtoConverter) {
    this.userRepository = userRepository;
    this.userDtoConverter = userDtoConverter;
  }

  public UserCredentialDto createUser(CreateUserRequest createUserRequest) {
    User user = new User(createUserRequest.isEmployer(), createUserRequest.getEmail(),
        createUserRequest.getPassword());
    return userDtoConverter.convertToUserCredentialDto(userRepository.save(user));
  }

  public void updateUser(Long id, UpdateUserRequest updateUserRequest)
      throws UserNotFoundException {
    User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    user.setFirstname(updateUserRequest.getFirstname());
    user.setLastname(updateUserRequest.getLastname());
    user.setEmail(updateUserRequest.getEmail());
    user.setGender(updateUserRequest.getGender());
    user.setPhoneNumber(updateUserRequest.getPhoneNumber());
    user.setProvince(updateUserRequest.getProvince());
    user.setDistrict(updateUserRequest.getDistrict());
    user.setExperience(updateUserRequest.getExperience());
    user.setAboutUser(updateUserRequest.getAboutUser());
    userRepository.save(user);

//    return userDtoConverter.convertToUserDetailsDto(userRepository.save(user));
  }

  public void deleteUser(Long id) throws UserNotFoundException {
    User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    userRepository.delete(user);
  }

  public void addApplicationToUser(User user, ApplicationDetail application) {
    user.addApplication(application);
    System.out.println(user);
    userRepository.save(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long id) throws UserNotFoundException {
    return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }
}
