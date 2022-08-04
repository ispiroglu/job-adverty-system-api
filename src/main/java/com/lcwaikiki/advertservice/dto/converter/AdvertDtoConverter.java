package com.lcwaikiki.advertservice.dto.converter;

import com.lcwaikiki.advertservice.dto.model.advert.AdvertCardInfoDto;
import com.lcwaikiki.advertservice.dto.model.advert.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.model.advert.DashboardAdvertTableInfoDto;
import com.lcwaikiki.advertservice.dto.request.advert.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.request.advert.GetFilteredAdvertsRequest;
import com.lcwaikiki.advertservice.dto.request.advert.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.dto.response.advert.AdminAdvertInfoResponse;
import com.lcwaikiki.advertservice.dto.response.advert.FilteredAdvertResponse;
import com.lcwaikiki.advertservice.dto.response.user.UserApplicationTableAdvertInfo;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.model.ApplicationStatus;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.sql.rowset.serial.SerialBlob;
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
//        createAdvertRequest.getPhoto(),
        createAdvertRequest.getCompanyName(),
        createAdvertRequest.getDepartment());
  }

  public Advert convertToAdvert(UpdateAdvertRequest updateAdvertRequest) throws ParseException {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    return new Advert(
        updateAdvertRequest.getName(), updateAdvertRequest.getSummary(),
        f.parse(updateAdvertRequest.getStartDate()),
        f.parse(updateAdvertRequest.getEndDate()),
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

  public UserApplicationTableAdvertInfo convertToAdvertInfo(Advert advert,
      ApplicationStatus status) {
    return new UserApplicationTableAdvertInfo(
        advert.getName(), advert.getPosition(), advert.getSummary(),
        advert.getDistrict() + "/" + advert.getProvince(), status
    );
  }

  public DashboardAdvertTableInfoDto convertToDashboardAdvertInfo(Advert advert) {
    return new DashboardAdvertTableInfoDto(
        advert.getCompanyName(), advert.getName(), advert.getPosition(), advert.getSummary(),
        advert.getDistrict() + "/" + advert.getProvince()
    );
  }

  public AdvertCardInfoDto convertToAdvertCardInfo(Advert advert) throws SQLException {
    return new AdvertCardInfoDto(
        advert.getId(), advert.getPhoto() != null ? (new SerialBlob(advert.getPhoto())) : null,
        advert.getName(), advert.getPosition(),
        advert.getSummary(),
        advert.getDistrict() + "/" + advert.getProvince()
    );
  }

  public AdminAdvertInfoResponse convertToAdminInfoResponse(Advert advert) {
    return new AdminAdvertInfoResponse(
        advert.getId(), advert.getName(), advert.getSummary(), advert.getStartDate().toString(),
        advert.getEndDate().toString(),
        advert.getPosition(), advert.getCapacity(), advert.getDistrict(), advert.getProvince(),
        advert.getProvinceID(),
        advert.getJobDefinition(), advert.isActive(), advert.getCompanyName(),
        advert.getDepartment()
    );
  }

  public UserApplicationTableAdvertInfo converToApplicationTableAdvertInfo(Advert advert,
      ApplicationStatus status) {
    return new UserApplicationTableAdvertInfo(
        advert.getName(), advert.getPosition(), advert.getSummary(),
        advert.getDistrict() + "/" + advert.getProvince(), status
    );
  }
}
