package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.dto.response.advert.DashboardInfoResponse;
import com.lcwaikiki.advertservice.exception.AdvertIsFullException;
import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.exception.UserAlreadyAppliedException;
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
      throws UserNotFoundException, AdvertNotFoundException, UserNotValidForApplicationException, AdvertIsFullException, UserAlreadyAppliedException {
    System.out.println(advertId + "Advert");
    System.out.println(userId + "User");
    User user = userService.findById(userId);

    if (!UserValidationUtil.isValidForApplication(user)) {
      throw new UserNotValidForApplicationException();
    }

    Advert advert = advertService.findById(advertId);
    if (!AdvertValidationUtil.isAdvertCapacityFull(advert)) {
      throw new AdvertIsFullException();
    }
    System.out.println("Advert Before Apply");
    System.out.println(advert);

    if (applicationDetailRepository.getApplicationDetailByAdvertAndUser(advert, user) != null)
      throw new UserAlreadyAppliedException();

    System.out.println("LOl");
    ApplicationDetail application = new ApplicationDetail(
        advert, user
    );

    userService.addApplicationToUser(user, application);
    advertService.addUserToAdvert(advert, application);
    System.out.println("After");
    System.out.println(advert);
    applicationDetailRepository.save(application);
  }

  public void updateApplicationStatus(Long advertId, Long userId, ApplicationStatus newStatus)
      throws AdvertNotFoundException, UserNotFoundException {
    ApplicationDetail application = applicationDetailRepository.getApplicationDetailByAdvertAndUser(
        advertService.findById(advertId), userService.findById(userId)
    );

    application.setStatus(newStatus);
    application.setDecided(true);
    applicationDetailRepository.save(application);
  }

  public DashboardInfoResponse getDashboardInfoDto() {
    return new DashboardInfoResponse(
        advertService.getAdvertCount(),
        userService.getUserCount(),
        1L, advertService.getEndingAdverts(), advertService.getStartingAdverts()
    );
  }
}
