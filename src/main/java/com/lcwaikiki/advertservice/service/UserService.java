package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.dto.converter.AdvertDtoConverter;
import com.lcwaikiki.advertservice.dto.converter.UserDtoConverter;
import com.lcwaikiki.advertservice.dto.model.user.UserCredentialDto;
import com.lcwaikiki.advertservice.dto.request.user.CreateUserRequest;
import com.lcwaikiki.advertservice.dto.request.user.UpdateUserRequest;
import com.lcwaikiki.advertservice.dto.response.user.UserApplicationTableAdvertInfo;
import com.lcwaikiki.advertservice.dto.response.user.UserDetailResponse;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.AdvertOwner;
import com.lcwaikiki.advertservice.model.ApplicationDetail;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.repository.UserRepository;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserDtoConverter userDtoConverter;
  private final AdvertDtoConverter advertDtoConverter;
  private final PasswordEncoder passwordEncoder;


  public UserService(UserRepository userRepository,
      UserDtoConverter userDtoConverter, AdvertDtoConverter advertDtoConverter,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userDtoConverter = userDtoConverter;
    this.advertDtoConverter = advertDtoConverter;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findUserByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("Email not found in database");
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.isEmployer() ? "employer" : "employee"));
    authorities.add(new SimpleGrantedAuthority("LOGGED_IN"));
    log.info("Auths -> {}", authorities);
    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(), authorities
    );
    return userDetails;
  }

  public UserCredentialDto createUser(CreateUserRequest createUserRequest) {
    User user = new User(createUserRequest.isEmployer(), createUserRequest.getEmail(),
        passwordEncoder.encode(createUserRequest.getPassword()), createUserRequest.getFirstname(),
        createUserRequest.getLastname());
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

  public User findByEmail(String email) {
    return userRepository.findUserByEmail(email);
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

  public void addAdvertOwnershipToUser(AdvertOwner advertOwner, User user)
      throws UserNotFoundException {
    user.addOwnedAdvert(advertOwner);
    userRepository.save(user);
  }


}
