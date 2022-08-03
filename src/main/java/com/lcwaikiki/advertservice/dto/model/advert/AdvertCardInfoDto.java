package com.lcwaikiki.advertservice.dto.model.advert;

public class AdvertCardInfoDto {

  private Long id;
  private byte[] image;
  private String advertName;
  private String position;
  private String summary;
  private String location;

  public AdvertCardInfoDto(Long id, byte[] image, String advertName, String position,
      String summary,
      String location) {
    this.id = id;
    this.image = image;
    this.advertName = advertName;
    this.position = position;
    this.summary = summary;
    this.location = location;
  }

  public byte[] getImage() {
    return image;
  }

  public String getAdvertName() {
    return advertName;
  }

  public Long getId() {
    return id;
  }

  public String getPosition() {
    return position;
  }

  public String getSummary() {
    return summary;
  }

  public String getLocation() {
    return location;
  }
}
