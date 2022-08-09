package com.lcwaikiki.advertservice.service;

import com.lcwaikiki.advertservice.dto.converter.AdvertDtoConverter;
import com.lcwaikiki.advertservice.dto.converter.UserDtoConverter;
import com.lcwaikiki.advertservice.dto.model.advert.AdvertCardInfoDto;
import com.lcwaikiki.advertservice.dto.model.advert.AdvertDetailsDto;
import com.lcwaikiki.advertservice.dto.model.advert.DashboardAdvertTableInfoDto;
import com.lcwaikiki.advertservice.dto.request.advert.CreateAdvertRequest;
import com.lcwaikiki.advertservice.dto.request.advert.GetFilteredAdvertsRequest;
import com.lcwaikiki.advertservice.dto.request.advert.UpdateAdvertRequest;
import com.lcwaikiki.advertservice.dto.response.advert.AdminAdvertInfoResponse;
import com.lcwaikiki.advertservice.dto.response.user.AdvertAppliedUserInfoResponse;
import com.lcwaikiki.advertservice.exception.AdvertNotFoundException;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.model.ApplicationDetail;
import com.lcwaikiki.advertservice.repository.AdvertRepository;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AdvertService {

  private final AdvertRepository advertRepository;
  private final AdvertDtoConverter advertDtoConverter;
  private final UserDtoConverter userDtoConverter;

  public AdvertService(AdvertRepository advertRepository,
      AdvertDtoConverter advertDtoConverter, UserDtoConverter userDtoConverter) {
    this.advertRepository = advertRepository;
    this.advertDtoConverter = advertDtoConverter;
    this.userDtoConverter = userDtoConverter;
  }

  public AdvertDetailsDto createAdvert(CreateAdvertRequest createAdvertRequest) {
    Advert advert = advertDtoConverter.convertToAdvert(createAdvertRequest);
//    advert.setActive(true);
    System.out.println(advert);
    return advertDtoConverter.convertToAdvertDetailsDto(advertRepository.save(advert));
  }

  public void updateAdvert(UpdateAdvertRequest updateAdvertRequest, Long id)
      throws AdvertNotFoundException, ParseException {
    Advert advert = advertRepository.findById(id).orElseThrow(AdvertNotFoundException::new);
    Advert advertToSave = advertDtoConverter.convertToAdvert(updateAdvertRequest);
    advertToSave.setCreationDate(advert.getCreationDate());
    advertToSave.setId(advert.getId());
    advertToSave.setUpdate(advert.getUpdate());
    advertToSave.setActive(advert.isActive());
    advertToSave.setPhoto(advert.getPhoto());
    advertRepository.save(advertToSave);
  }

  public void deleteAdvert(long id) throws AdvertNotFoundException {
    Advert advert = advertRepository.findById(id).orElseThrow(AdvertNotFoundException::new);
    advert.setActive(false);
    advertRepository.save(advert);
  }

  public List<AdvertCardInfoDto> findFilteredAdverts(GetFilteredAdvertsRequest request) {
    List<AdvertCardInfoDto> list = new ArrayList<>();
    advertRepository.findAdvertsByFullFilter(
            request.getProvince(),
            request.getPosition(),
            request.getDepartment(),
            request.getSearchText())
        .stream().forEach(advert -> {
          try {
            list.add(
                advertDtoConverter.convertToAdvertCardInfo(advert)
            );
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
        });
    return list;
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
      throws AdvertNotFoundException, IOException, SQLException {
    Advert advert = findById(id);
    advert.setPhoto((file.getBytes()));
    advertRepository.save(advert);
  }

  public Blob getAdvertPhoto(Long id) throws AdvertNotFoundException, SQLException {
    return new SerialBlob(findById(id).getPhoto());
  }

  public Long getAdvertCount() {
    return advertRepository.count();
  }

  public List<DashboardAdvertTableInfoDto> getEndingAdverts() {
    long DAY_IN_MS = 1000 * 60 * 60 * 24;
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis() + (7 * DAY_IN_MS));
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    List<DashboardAdvertTableInfoDto> list = new ArrayList<>();
    advertRepository.findEndingAdverts(sqlDate)
        .forEach(advert -> list.add(advertDtoConverter.convertToDashboardAdvertInfo(advert)));
    return list;
  }

  public List<DashboardAdvertTableInfoDto> getStartingAdverts() {
    long DAY_IN_MS = 1000 * 60 * 60 * 24;
    java.util.Date utilDate = new java.util.Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    List<DashboardAdvertTableInfoDto> list = new ArrayList<>();
    advertRepository.findStartingAdverts(sqlDate)
        .forEach(advert -> list.add(advertDtoConverter.convertToDashboardAdvertInfo(advert)));
    return list;
  }

  public List<AdvertCardInfoDto> getAdvertCards() {
    List<AdvertCardInfoDto> list = new ArrayList<>();

    findAll().stream().filter(Advert::isActive).forEach(advert -> {
      try {
        list.add(advertDtoConverter.convertToAdvertCardInfo(advert));
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    return list;
  }

  public AdminAdvertInfoResponse getAdvertInfo(Long id) throws AdvertNotFoundException {
    return advertDtoConverter.convertToAdminInfoResponse(findById(id));
  }

  public List<AdvertAppliedUserInfoResponse> getApplicants(Long id) throws AdvertNotFoundException {
    List<AdvertAppliedUserInfoResponse> list = new ArrayList<>();
    findById(id).getApplications().stream()
        .filter(applicationDetail -> !applicationDetail.isDecided())
        .forEach(applicationDetail -> list.add(
            userDtoConverter.convertToAdvertAppliedUserInfoResponse(applicationDetail.getUser())));
    return list;
  }
}
