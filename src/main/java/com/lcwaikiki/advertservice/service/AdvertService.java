package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.dto.converter.AdvertDtoConverter;
import com.lcwaikiki.advertservice.dto.model.advert.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.request.advert.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.request.advert.GetFilteredAdvertsRequest;
import com.lcwaikiki.advertservice.dto.request.advert.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.model.ApplicationDetail;
import com.lcwaikiki.advertservice.repository.AdvertRepository;
import java.io.IOException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AdvertService {

  private final AdvertRepository advertRepository;
  private final ModelMapper modelMapper;
  private final AdvertDtoConverter advertDtoConverter;

  public AdvertService(AdvertRepository advertRepository,
      ModelMapper modelMapper,
      AdvertDtoConverter advertDtoConverter) {
    this.advertRepository = advertRepository;
    this.modelMapper = modelMapper;
    this.advertDtoConverter = advertDtoConverter;
  }

  public AdvertDetailsDto createAdvert(CreateAdvertRequest createAdvertRequest) {
    Advert advert = advertDtoConverter.convertToAdvert(createAdvertRequest);
//    advert.setActive(true);
    System.out.println(advert);
    return advertDtoConverter.convertToAdvertDetailsDto(advertRepository.save(advert));
  }

  public void updateAdvert(UpdateAdvertRequest updateAdvertRequest, Long id)
      throws AdvertNotFoundException {
    Advert advert = advertRepository.findById(id).orElseThrow(AdvertNotFoundException::new);
    Advert advertToSave = advertDtoConverter.convertToAdvert(updateAdvertRequest);
    advertToSave.setCreationDate(advert.getCreationDate());
    advertToSave.setId(advert.getId());
    advertRepository.save(advertToSave);
//    advert.setName(updateAdvertRequest.getName());
//    advert.setSummary( updateAdvertRequest.getSummary());
//    advert.setStartDate(updateAdvertRequest.getStartDate());
//    advert.setEndDate( updateAdvertRequest.getEndDate());
//    advert.setPosition(updateAdvertRequest.getPosition());
//    advert.setCapacity(updateAdvertRequest.getCapacity());
//    advert.setDistrict(updateAdvertRequest.getDistrict());
//    advert.setProvince(updateAdvertRequest.getProvince());
//    advert.setProvinceID(updateAdvertRequest.getProvinceID());
//    advert.setJobDefinition(updateAdvertRequest.getJobDefinition());
//    advert.setActive(updateAdvertRequest.isActive());
//    advert.setPhotoUrl(updateAdvertRequest.getPhotoUrl());
//    advert.setCompanyName(updateAdvertRequest.getCompanyName());
//    advert.setDepartment(updateAdvertRequest.getDepartment());
  }

  public void deleteAdvert(long id) throws AdvertNotFoundException {
    Advert advert = advertRepository.findById(id).orElseThrow(AdvertNotFoundException::new);
    advert.setActive(false);
    advertRepository.save(advert);
  }

  public List<Advert> findFilteredAdverts(GetFilteredAdvertsRequest request) {
    return advertRepository.findAdvertsByFullFilter(request.getProvince(), request.getPosition(),
        request.getDepartment(), request.getSearchText());
  }

  public void addUserToAdvert(Advert advert, ApplicationDetail application) {
    advert.addApplication(application);
    advertRepository.save(advert);
  }

  public List<Advert> findAll() {
    return advertRepository.findAll();
  }

  public Advert findById(long id) throws AdvertNotFoundException {
    return advertRepository.findById(id).orElseThrow(AdvertNotFoundException::new);
  }

  public void updateAdvertPhoto(MultipartFile file, Long id)
      throws AdvertNotFoundException, IOException {
    Advert advert = findById(id);
    advert.setPhoto(file.getBytes());
    advertRepository.save(advert);
  }

  public byte[] getAdvertPhoto(Long id) throws AdvertNotFoundException {
    return findById(id).getPhoto();
  }
}
