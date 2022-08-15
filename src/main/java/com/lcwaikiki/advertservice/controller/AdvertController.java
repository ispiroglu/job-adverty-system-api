package com.lcwaikiki.advertservice.controller;

import com.lcwaikiki.advertservice.dto.model.advert.AdvertCardInfoDto;
import com.lcwaikiki.advertservice.dto.model.advert.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.model.advert.DashboardAdvertTableInfoDto;
import com.lcwaikiki.advertservice.dto.request.advert.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.request.advert.GetFilteredAdvertsRequest;
import com.lcwaikiki.advertservice.dto.request.advert.UpdateAdvertPhotoRequest;
import com.lcwaikiki.advertservice.dto.request.advert.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.dto.request.applicationdetail.UpdateApplicationStatusRequest;
import com.lcwaikiki.advertservice.dto.response.advert.AdminAdvertInfoResponse;
import com.lcwaikiki.advertservice.dto.response.user.AdvertAppliedUserInfoResponse;
import com.lcwaikiki.advertservice.exception.AdvertIsFullException;
import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.exception.UserAlreadyAppliedException;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.exception.UserNotValidForApplicationException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.service.AdvertService;
import com.lcwaikiki.advertservice.service.OperationHandlerService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/adverts")
@Slf4j
public class AdvertController {

  private final AdvertService advertService;
  private final OperationHandlerService operationHandlerService;

  public AdvertController(AdvertService advertService,
      OperationHandlerService operationHandlerService) {
    this.advertService = advertService;
    this.operationHandlerService = operationHandlerService;
  }

  @GetMapping("/allAdverts")
  public List<Advert> findAll() {
    return advertService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Advert> findById(@PathVariable long id) throws AdvertNotFoundException {
    return ResponseEntity.ok(advertService.findById(id));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<AdvertDetailsDto> create(
      @RequestBody CreateAdvertRequest createAdvertRequest, @RequestParam Long creatorID)
      throws UserNotFoundException {
    log.info("Create Advert Request -> {}", creatorID);
    return ResponseEntity.ok(operationHandlerService.createAdvert(createAdvertRequest, creatorID));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PatchMapping("/{id}/adminView")
  public void update(@RequestBody UpdateAdvertRequest updateAdvertRequest,
      @PathVariable long id)
      throws AdvertNotFoundException, ParseException {
    advertService.updateAdvert(updateAdvertRequest, id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable long id) throws AdvertNotFoundException {
    System.out.println("Delete");
    advertService.deleteAdvert(id);
  }

  @PatchMapping("/filter") // Patch ??
  public ResponseEntity<List<AdvertCardInfoDto>> findFilteredAdverts(
      @RequestBody GetFilteredAdvertsRequest request) {
    System.out.println(request);
    return ResponseEntity.ok(advertService.findFilteredAdverts(request));
  }

  @PostMapping("/{id}/applications")
  public void addUserToAdvert(@RequestBody Long userID, @PathVariable Long id)
      throws UserNotFoundException, AdvertNotFoundException, UserNotValidForApplicationException, AdvertIsFullException, UserAlreadyAppliedException {
    operationHandlerService.addApplicantToAdvert(id, userID);
  }

  @GetMapping("/{id}/applications")
  public ResponseEntity<List<AdvertAppliedUserInfoResponse>> getApplicants(@PathVariable Long id)
      throws AdvertNotFoundException {
    return ResponseEntity.ok(advertService.getApplicants(id));
  }

  @PatchMapping("/{id}/applications")
  public void updateApplicationStatus(@RequestBody UpdateApplicationStatusRequest request,
      @PathVariable Long id) throws UserNotFoundException, AdvertNotFoundException {
//    System.out.println(request);
    operationHandlerService.updateApplicationStatus(id, request.getUserId(),
        request.getNewStatus());
  }

  @PatchMapping("/{id}/photo")
  public void updateAdvertPhoto(UpdateAdvertPhotoRequest request,
      @PathVariable Long id)
      throws IOException, AdvertNotFoundException, SQLException {
    System.out.println(request.getFile());
    advertService.updateAdvertPhoto(request.getFile(), id);
  }

  @GetMapping("/{id}/photo")
  public ResponseEntity<Blob> getAdvertPhoto(@PathVariable Long id)
      throws AdvertNotFoundException, SQLException {
    return ResponseEntity.ok()
        .body(advertService.getAdvertPhoto(id));
  }

  @GetMapping("/count")
  public ResponseEntity<Long> getAdvertCount() {
    return ResponseEntity.ok(advertService.getAdvertCount());
  }

  @GetMapping("/soonEndingAdverts")
  public ResponseEntity<List<DashboardAdvertTableInfoDto>> getSoonEndings() {
    return ResponseEntity.ok(advertService.getEndingAdverts());
  }

  @GetMapping
  public ResponseEntity<Page<AdvertCardInfoDto>> getAdvertCards(@RequestParam int page,
      @RequestParam Long creatorID) throws UserNotFoundException {
    log.info("123");
    return ResponseEntity.ok(advertService.getAdvertCards(page, creatorID));
  }

  @GetMapping("/{id}/adminView")
  public ResponseEntity<AdminAdvertInfoResponse> getAdvertInfo(@PathVariable Long id)
      throws AdvertNotFoundException {
    return ResponseEntity.ok(advertService.getAdvertInfo(id));
  }

}
