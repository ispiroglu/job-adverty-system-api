package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.exception.AdvertIsFullException;
import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.exception.UserNotValidForApplicationException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.model.ApplicationDetail;
import com.lcwaikiki.advertservice.model.ApplicationStatus;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.repository.ApplicationDetailRepository;
import com.lcwaikiki.advertservice.util.advert.AdvertValidationUtil;
import com.lcwaikiki.advertservice.util.user.UserValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class OperationHandlerService {

  private final UserService userService;
  private final AdvertService advertService;
  private final ApplicationDetailRepository applicationDetailRepository;

  public OperationHandlerService(UserService userService, AdvertService advertService,
      ApplicationDetailRepository applicationDetailRepository) {
    this.userService = userService;
    this.advertService = advertService;
    this.applicationDetailRepository = applicationDetailRepository;
  }

  public void addApplicantToAdvert(Long advertId, Long userId)
      throws UserNotFoundException, AdvertNotFoundException, UserNotValidForApplicationException, AdvertIsFullException {
    User user = userService.findById(userId);

    if (!UserValidationUtil.isValidForApplication(user))
      throw new UserNotValidForApplicationException();

    Advert advert = advertService.findById(advertId);
    if (!AdvertValidationUtil.isAdvertCapacityFull(advert))
      throw new AdvertIsFullException();

    ApplicationDetail application = new ApplicationDetail(
        advert, user
    );

    userService.addApplicationToUser(user, application);
    advertService.addUserToAdvert(advert, application);
    applicationDetailRepository.save(application);
  }

  public void updateApplicationStatus(Long advertId, Long userId, ApplicationStatus newStatus)
      throws AdvertNotFoundException, UserNotFoundException {
    ApplicationDetail application = applicationDetailRepository.getApplicationDetailByAdvertAndUser(
        advertService.findById(advertId), userService.findById(userId)
    );

    application.setStatus(newStatus);
    applicationDetailRepository.save(application);
  }
}
