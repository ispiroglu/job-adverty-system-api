package com.lcwaikiki.advertservice.dto;

import java.util.Date;

public class UpdateAdvertRequest {


  private String name;

  private String summary;

  private Date startDate;

  private Date endDate;

  private String position;

  private int capacity;

  private String district;

  private String province;

  private int provinceID;

  private String jobDefinition;

  private boolean isActive;

  private String photoUrl;

  private String companyName;

  private String department;


  public UpdateAdvertRequest(String name, String summary, Date startDate, Date endDate,
      String position, int capacity, String district, String province, int provinceID,
      String jobDefinition, boolean isActive, String photoUrl, String companyName,
      String department) {
    this.name = name;
    this.summary = summary;
    this.startDate = startDate;
    this.endDate = endDate;
    this.position = position;
    this.capacity = capacity;
    this.district = district;
    this.province = province;
    this.provinceID = provinceID;
    this.jobDefinition = jobDefinition;
    this.isActive = isActive;
    this.photoUrl = photoUrl;
    this.companyName = companyName;
    this.department = department;
  }

  public String getName() {
    return name;
  }

  public String getSummary() {
    return summary;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getPosition() {
    return position;
  }

  public int getCapacity() {
    return capacity;
  }

  public String getDistrict() {
    return district;
  }

  public String getProvince() {
    return province;
  }

  public int getProvinceID() {
    return provinceID;
  }

  public String getJobDefinition() {
    return jobDefinition;
  }

  public boolean isActive() {
    return isActive;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getDepartment() {
    return department;
  }
}
