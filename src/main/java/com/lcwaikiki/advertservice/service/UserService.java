package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.dto.converter.AdvertDtoConverter;
import com.lcwaikiki.advertservice.dto.converter.UserDtoConverter;
import com.lcwaikiki.advertservice.dto.model.user.UserCredentialDto;
import com.lcwaikiki.advertservice.dto.request.user.CreateUserRequest;
import com.lcwaikiki.advertservice.dto.request.user.UpdateUserRequest;
import com.lcwaikiki.advertservice.dto.response.user.UserApplicationsResponse;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.ApplicationDetail;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.repository.UserRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserDtoConverter userDtoConverter;
  private final AdvertDtoConverter advertDtoConverter;

  public UserService(UserRepository userRepository,
      UserDtoConverter userDtoConverter, AdvertDtoConverter advertDtoConverter) {
    this.userRepository = userRepository;
    this.userDtoConverter = userDtoConverter;
    this.advertDtoConverter = advertDtoConverter;
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

  public void updateUserProfilePicture(MultipartFile file, Long id)
      throws IOException, UserNotFoundException {

    User user = findById(id);
    user.setProfilePhoto(file.getBytes());
    userRepository.save(user);
  }

  public byte[] getUserProfilePhoto(Long id) throws UserNotFoundException {
    return findById(id).getProfilePhoto();
  }

  public void updateUserCv(MultipartFile file, Long id) throws IOException, UserNotFoundException {
    User user = findById(id);
    user.setCv(file.getBytes());
    userRepository.save(user);
  }

  public byte[] getUserCv(Long id) throws UserNotFoundException {
    return findById(id).getCv();
  }


  // TODO: Should return basic infos about adverts. CREATE appliedAdvertsResponse : DONE
  public UserApplicationsResponse getAppliedAdverts(Long id) throws UserNotFoundException {
    User user = findById(id);
    UserApplicationsResponse response = new UserApplicationsResponse();

    user.getApplications()
        .forEach(applicationDetail -> response.addToAdvertInfos(
            advertDtoConverter.convertToAdvertInfo(applicationDetail.getAdvert(),
                applicationDetail.getStatus())
        ));
    return response;
  }
}
