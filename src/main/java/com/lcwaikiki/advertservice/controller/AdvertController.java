package com.lcwaikiki.advertservice.controller;

import com.lcwaikiki.advertservice.dto.model.advert.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.request.advert.AddApplicantRequest;
import com.lcwaikiki.advertservice.dto.request.advert.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.request.advert.GetFilteredAdvertsRequest;
import com.lcwaikiki.advertservice.dto.request.advert.UpdateAdvertPhotoRequest;
import com.lcwaikiki.advertservice.dto.request.advert.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.dto.request.applicationdetail.UpdateApplicationStatusRequest;
import com.lcwaikiki.advertservice.exception.AdvertIsFullException;
import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.exception.UserNotFoundException;
import com.lcwaikiki.advertservice.exception.UserNotValidForApplicationException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.service.AdvertService;
import com.lcwaikiki.advertservice.service.OperationHandlerService;
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
@RequestMapping("/api/v1/adverts")
public class AdvertController {

  private final AdvertService advertService;
  private final OperationHandlerService operationHandlerService;

  public AdvertController(AdvertService advertService,
      OperationHandlerService operationHandlerService) {
    this.advertService = advertService;
    this.operationHandlerService = operationHandlerService;
  }

  @GetMapping
  public List<Advert> findAll() {
    return advertService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Advert> findById(@PathVariable long id) throws AdvertNotFoundException {
    return ResponseEntity.ok(advertService.findById(id));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PutMapping
  public ResponseEntity<AdvertDetailsDto> create(
      @RequestBody CreateAdvertRequest createAdvertRequest) {
    return ResponseEntity.ok(advertService.createAdvert(createAdvertRequest));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping("/{id}")
  public void update(@Valid @RequestBody UpdateAdvertRequest updateAdvertRequest,
      @PathVariable long id)
      throws AdvertNotFoundException {
    advertService.updateAdvert(updateAdvertRequest, id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable long id) throws AdvertNotFoundException {
    advertService.deleteAdvert(id);
  }

  @GetMapping("/filter")
  public List<Advert> findFilteredAdverts(GetFilteredAdvertsRequest request) {
    return advertService.findFilteredAdverts(request);
  }

  @PutMapping("/{id}/applications")
  public void addUserToAdvert(AddApplicantRequest request, @PathVariable Long id)
      throws UserNotFoundException, AdvertNotFoundException, UserNotValidForApplicationException, AdvertIsFullException {
    operationHandlerService.addApplicantToAdvert(id, request.getUserId());
  }

  @PostMapping("/{id}/applications")
  public void updateApplicationStatus(UpdateApplicationStatusRequest request,
      @PathVariable Long id) throws UserNotFoundException, AdvertNotFoundException {
    operationHandlerService.updateApplicationStatus(id, request.getUserId(),
        request.getNewStatus());
  }

  @PostMapping("/{id}/photo")
  public void updateAdvertPhoto(UpdateAdvertPhotoRequest request, @PathVariable Long id)
      throws IOException, AdvertNotFoundException {
    System.out.println("Allah var");
    System.out.println(request);
    advertService.updateAdvertPhoto(request.getFile(), id);
  }

  @GetMapping("/{id}/photo")
  public ResponseEntity<byte[]> getAdvertPhoto(@PathVariable Long id)
      throws AdvertNotFoundException {
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
        .body(advertService.getAdvertPhoto(id));
  }
}
