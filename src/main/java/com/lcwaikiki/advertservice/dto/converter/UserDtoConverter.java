package com.lcwaikiki.advertservice.dto.converter;

import com.lcwaikiki.advertservice.dto.model.user.UserCredentialDto;
import com.lcwaikiki.advertservice.dto.model.user.UserDetailsDto;
import com.lcwaikiki.advertservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

  public UserCredentialDto convertToUserCredentialDto(User from) {
    return new UserCredentialDto(
        from.getId(),
        from.isEmployer(),
        from.getEmail(),
        from.getPassword(),
        from.getCreationDate()
    );
  }

  public UserDetailsDto convertToUserDetailsDto(User from) {
    return new UserDetailsDto(
        from.getFirstname(), from.getLastname(),
        from.getGender(), from.getEmail(),
        from.getPhoneNumber(), from.getProvince(),
        from.getDistrict(), from.getExperience(), from.getAboutUser()
    );
  }

  /*
   * Should implement
   *    convertToUserCVDto
   *    convertToUserPhotoDto
   *    convertToUserApplicationsDto
   * */
}
