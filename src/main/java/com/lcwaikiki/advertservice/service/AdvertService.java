package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.dto.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.dto.converter.AdvertDtoConverter;
import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.repository.AdvertRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


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

  public List<Advert> findAll() {
    return advertRepository.findAll();
  }

  public Advert findById(long id) throws AdvertNotFoundException {
    return advertRepository.findById(id).orElseThrow(AdvertNotFoundException::new);
  }
}
