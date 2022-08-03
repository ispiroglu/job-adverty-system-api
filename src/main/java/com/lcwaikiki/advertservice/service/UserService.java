package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.dto.converter.AdvertDtoConverter;
import com.lcwaikiki.advertservice.dto.converter.UserDtoConverter;
import com.lcwaikiki.advertservice.dto.model.user.UserCredentialDto;
import com.lcwaikiki.advertservice.dto.request.user.CreateUserRequest;
import com.lcwaikiki.advertservice.dto.request.user.UpdateUserRequest;
import com.lcwaikiki.advertservice.dto.response.user.UserApplicationTableAdvertInfo;
import com.lcwaikiki.advertservice.dto.response.user.UserDetailResponse;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.ApplicationDetail;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.repository.UserRepository;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
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
    user.setProvinceID(updateUserRequest.getProvinceID());
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

  public UserDetailResponse getUserDetail(Long id) throws UserNotFoundException {
    return userDtoConverter.convertToUserDetailsDto(findById(id));
  }

  public void updateUserProfilePicture(MultipartFile file, Long id)
      throws IOException, UserNotFoundException {

    User user = findById(id);
    user.setProfilePhoto(file.getBytes());
    userRepository.save(user);
  }

  public Blob getUserProfilePhoto(Long id) throws UserNotFoundException, SQLException {
    return new SerialBlob(findById(id).getProfilePhoto());
  }

  public void updateUserCv(MultipartFile file, Long id) throws IOException, UserNotFoundException {
    User user = findById(id);
    user.setCv(file.getBytes());
    userRepository.save(user);
  }

  public Blob getUserCv(Long id) throws UserNotFoundException, SQLException {
    return new SerialBlob(findById(id).getCv());
  }


  public List<UserApplicationTableAdvertInfo> getAppliedAdverts(Long id)
      throws UserNotFoundException {
    User user = findById(id);
    List<UserApplicationTableAdvertInfo> list = new ArrayList<>();

    user.getApplications()
        .forEach(applicationDetail ->
            list.add(advertDtoConverter.converToApplicationTableAdvertInfo(
                applicationDetail.getAdvert(), applicationDetail.getStatus())));
    return list;
  }

  public Long getUserCount() {
    return userRepository.count();
  }
}
