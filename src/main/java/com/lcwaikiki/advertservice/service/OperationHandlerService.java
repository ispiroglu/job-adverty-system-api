package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.model.ApplicationDetail;
import com.lcwaikiki.advertservice.model.User;
import com.lcwaikiki.advertservice.repository.ApplicationDetailRepository;
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

  public void addApplicantToAdvert(Long userId, Long advertId)
      throws UserNotFoundException, AdvertNotFoundException {
    User user = userService.findById(userId);
    Advert advert = advertService.findById(advertId);

    ApplicationDetail application = new ApplicationDetail(
        advert, user
    );
    System.out.println(application);

    userService.addApplicationToUser(user, application);
    advertService.addUserToAdvert(advert, application);
    applicationDetailRepository.save(application);
  }
}
