package com.lcwaikiki.advertservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDetailsDto {

  private String firstName;
  private String lastName;
  private String gender;
  @NotNull(message = "email cannot be null")
  @NotEmpty(message = "email cannot be empty")
  private String email;
  private String phoneNumber;
  private String province;
  private String district;
  private int experience;
  private String aboutUser;

  public UserDetailsDto(String firstName, String lastName, String gender, String email,
      String phoneNumber, String province, String district, int experience, String aboutUser) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.province = province;
    this.district = district;
    this.experience = experience;
    this.aboutUser = aboutUser;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public String getAboutUser() {
    return aboutUser;
  }

  public void setAboutUser(String aboutUser) {
    this.aboutUser = aboutUser;
  }
}
