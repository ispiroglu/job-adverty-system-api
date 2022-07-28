package com.lcwaikiki.advertservice.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "[user]")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstname;
  private String lastname;
  private String gender;
  @Column(unique = true)
  private String email;
  private String password;
  private String phoneNumber;
  private String province;
  private int provinceID;
  private String district;
  private int experience;
  private String aboutUser;
  private boolean isEmployer;
  @CreationTimestamp
  private LocalDateTime creationDate;
  @UpdateTimestamp
  private LocalDateTime update;

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

//  @ManyToMany
//  @JoinColumn(name = "advert_id")
//  private List<ApplicationDetail> applicationDetails;


  public User(Long id, String firstname, String lastname, String gender, String email,
      String password,
      String phoneNumber, String province, int provinceID, String district, int experience,
      String aboutUser, List<ApplicationDetail> applicationDetails, boolean isEmployer,
      LocalDateTime update) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.gender = gender;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.province = province;
    this.provinceID = provinceID;
    this.district = district;
    this.experience = experience;
    this.aboutUser = aboutUser;
//    this.applicationDetails = applicationDetails;
    this.isEmployer = isEmployer;
    this.update = update;
  }

  public User(boolean isEmployer, String email, String password) {
    this.isEmployer = isEmployer;
    this.email = email;
    this.password = password;
  }

  public User() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public int getProvinceID() {
    return provinceID;
  }

  public void setProvinceID(int provinceID) {
    this.provinceID = provinceID;
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

//  public List<ApplicationDetail> getApplicationDetails() {
//    return applicationDetails;
//  }
//
//  public void setApplicationDetails(List<ApplicationDetail> applicationDetails) {
//    this.applicationDetails = applicationDetails;
//  }

  public boolean isEmployer() {
    return isEmployer;
  }

  public void setEmployer(boolean employer) {
    isEmployer = employer;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", gender='" + gender + '\'' +
        ", email='" + email + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", province='" + province + '\'' +
        ", provinceID=" + provinceID +
        ", district='" + district + '\'' +
        ", experience=" + experience +
        ", aboutUser='" + aboutUser + '\'' +
//        ", applicationDetails=" + applicationDetails +
        '}';
  }

  public LocalDateTime getUpdate() {
    return update;
  }

  public void setUpdate(LocalDateTime update) {
    this.update = update;
  }
}
