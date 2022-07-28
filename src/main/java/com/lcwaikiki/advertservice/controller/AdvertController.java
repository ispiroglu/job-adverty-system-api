package com.lcwaikiki.advertservice.controller;

import com.lcwaikiki.advertservice.dto.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.service.AdvertService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
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

  public AdvertController(AdvertService advertService) {
    this.advertService = advertService;
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

//  @ResponseStatus(HttpStatus.CREATED)
//  @PutMapping("/{id}")
//  public Advert addApplicant(@RequestBody User applicant, @PathVariable Long id)
//      throws AdvertNotFoundException {
//    Advert advert = advertRepository.findById(id).orElseThrow(AdvertNotFoundException::new);
//    advert.addApplicantToAdvert(applicant);
//
//    return advertRepository.save(advert);
//  }
}
