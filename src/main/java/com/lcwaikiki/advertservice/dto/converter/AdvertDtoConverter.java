package com.lcwaikiki.advertservice.dto.converter;

import com.lcwaikiki.advertservice.dto.model.advert.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.model.advert.AdvertInfo;
import com.lcwaikiki.advertservice.dto.request.advert.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.request.advert.GetFilteredAdvertsRequest;
import com.lcwaikiki.advertservice.dto.request.advert.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.dto.response.advert.FilteredAdvertResponse;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.model.ApplicationStatus;
import org.springframework.stereotype.Component;

@Component
public class AdvertDtoConverter {

  public AdvertDetailsDto convertToAdvertDetailsDto(Advert advert) {
    return new AdvertDetailsDto(
        advert.getId(), advert.getName(), advert.getSummary(),
        advert.getStartDate(), advert.getEndDate(), advert.getPosition(),
        advert.getCapacity(), advert.getDistrict(), advert.getProvince(),
        advert.getProvinceID(), advert.getJobDefinition(), advert.isActive(),
        advert.getCompanyName(), advert.getDepartment()
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
        true,
        createAdvertRequest.getPhoto(), createAdvertRequest.getCompanyName(),
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
//        updateAdvertRequest.getPhoto(),
        updateAdvertRequest.getCompanyName(),
        updateAdvertRequest.getDepartment());
  }

  public Advert convertToAdvert(GetFilteredAdvertsRequest request) {
    Advert advert = new Advert();
    advert.setDepartment(request.getDepartment());
    advert.setProvince(request.getProvince());
    advert.setPosition(request.getPosition());

    return advert;
  }

  public FilteredAdvertResponse convertToFilteredAdvertResponse(Advert advert) {
    return new FilteredAdvertResponse(
        advert.getId(), advert.getName(), advert.getSummary(), advert.getStartDate(),
        advert.getEndDate(),
        advert.getPosition(), advert.getCapacity(), advert.getDistrict(), advert.getProvince(),
        advert.getProvinceID(), advert.getJobDefinition(), advert.isActive(), advert.getPhoto(),
        advert.getCompanyName(), advert.getDepartment(), advert.getApplications());
  }

  public AdvertInfo convertToAdvertInfo(Advert advert, ApplicationStatus status) {
    return new AdvertInfo(
        advert.getName(), advert.getPosition(), advert.getSummary(),
        advert.getDistrict() + "/" + advert.getProvince(), status
    );
  }
}
