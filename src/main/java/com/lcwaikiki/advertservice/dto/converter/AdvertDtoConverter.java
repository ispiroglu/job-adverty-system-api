package com.lcwaikiki.advertservice.dto.converter;

import com.lcwaikiki.advertservice.dto.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.model.Advert;
import org.springframework.stereotype.Component;

@Component
public class AdvertDtoConverter {

  public AdvertDetailsDto convertToAdvertDetailsDto(Advert advert) {
    return new AdvertDetailsDto(
        advert.getId(), advert.getName(), advert.getSummary(),
        advert.getStartDate(), advert.getEndDate(), advert.getPosition(),
        advert.getCapacity(), advert.getDistrict(), advert.getProvince(),
        advert.getProvinceID(), advert.getJobDefinition(), advert.isActive(),
        advert.getPhotoUrl(), advert.getCompanyName(), advert.getDepartment()
    );
  }

  public Advert convertToAdvert(CreateAdvertRequest createAdvertRequest) {
    return new Advert(
        createAdvertRequest.getName(), createAdvertRequest.getSummary(),
        createAdvertRequest.getStartDate(), createAdvertRequest.getEndDate(),
        createAdvertRequest.getPosition(),
        createAdvertRequest.getCapacity(), createAdvertRequest.getDistrict(),
        createAdvertRequest.getProvince(),
        createAdvertRequest.getProvinceID(), createAdvertRequest.getJobDefinition(),
        createAdvertRequest.isActive(),
        createAdvertRequest.getPhotoUrl(), createAdvertRequest.getCompanyName(),
        createAdvertRequest.getDepartment());
  }

  public Advert convertToAdvert(UpdateAdvertRequest updateAdvertRequest) {
    return new Advert(
        updateAdvertRequest.getName(), updateAdvertRequest.getSummary(),
        updateAdvertRequest.getStartDate(), updateAdvertRequest.getEndDate(),
        updateAdvertRequest.getPosition(),
        updateAdvertRequest.getCapacity(), updateAdvertRequest.getDistrict(),
        updateAdvertRequest.getProvince(),
        updateAdvertRequest.getProvinceID(), updateAdvertRequest.getJobDefinition(),
        updateAdvertRequest.isActive(),
        updateAdvertRequest.getPhotoUrl(), updateAdvertRequest.getCompanyName(),
        updateAdvertRequest.getDepartment());
  }
}
