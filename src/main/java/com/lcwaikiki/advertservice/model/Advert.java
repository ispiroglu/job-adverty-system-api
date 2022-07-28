package com.lcwaikiki.advertservice.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Advert {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String name;
  @Column
  private String summary;
  @Column
  private Date startDate;
  @Column
  private Date endDate;
  @Column
  private String position;
  @Column
  private int capacity;
  @Column
  private String district;
  @Column
  private String province;
  @Column
  private int provinceID;
  @Column
  private String jobDefinition;
  @Column
  private boolean isActive;
  @Column
  private String photoUrl;
  @Column
  private String companyName;
  @Column
  private String department;
  @CreationTimestamp
  private LocalDateTime creationDate;
  @UpdateTimestamp
  private LocalDateTime update;

  @Column
  @OneToMany
  private List<User> applicants;

  public Advert(Long id, String name, String summary, Date startDate, Date endDate,
      String position, int capacity, String district, String province, int provinceID,
      String jobDefinition, boolean isActive, String photoUrl, String companyName,
      String department, LocalDateTime creationDate, LocalDateTime update, List<User> applicants) {
    this.id = id;
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
    this.creationDate = creationDate;
    this.update = update;
    this.applicants = applicants;
  }

  public Advert() {

  }

  public Advert(String name, String summary, Date startDate, Date endDate, String position,
      int capacity, String district, String province, int provinceID, String jobDefinition,
      boolean isActive, String photoUrl, String companyName, String department) {
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
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

  public String getJobDefinition() {
    return jobDefinition;
  }

  public void setJobDefinition(String jobDefinition) {
    this.jobDefinition = jobDefinition;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public List<User> getApplicants() {
    return applicants;
  }

  public void setApplicants(List<User> applicants) {
    this.applicants = applicants;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public LocalDateTime getUpdate() {
    return update;
  }

  public void setUpdate(LocalDateTime update) {
    this.update = update;
  }

  public Advert updateAdvert(Advert baseAdvert, Advert newAdvert) {
    baseAdvert.setName(newAdvert.getName());
    return baseAdvert;
  }

  public User addApplicantToAdvert(User applicant) {
    applicants.add(applicant);
    return applicant;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Advert advert = (Advert) o;
    return capacity == advert.capacity && provinceID == advert.provinceID
        && isActive == advert.isActive && id.equals(advert.id) && name.equals(advert.name)
        && summary.equals(advert.summary) && startDate.equals(advert.startDate)
        && endDate.equals(advert.endDate) && position.equals(advert.position)
        && district.equals(advert.district) && province.equals(advert.province)
        && jobDefinition.equals(advert.jobDefinition) && photoUrl.equals(advert.photoUrl)
        && companyName.equals(advert.companyName) && department.equals(advert.department)
        && applicants.equals(advert.applicants);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, summary, startDate, endDate, position, capacity, district,
        province, provinceID, jobDefinition, isActive, photoUrl, companyName, department,
        applicants);
  }

  @Override
  public String toString() {
    return "Advert{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", summary='" + summary + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", position='" + position + '\'' +
        ", capacity=" + capacity +
        ", district='" + district + '\'' +
        ", province='" + province + '\'' +
        ", provinceID=" + provinceID +
        ", jobDefinition='" + jobDefinition + '\'' +
        ", isOpen=" + isActive +
        ", photoUrl='" + photoUrl + '\'' +
        ", companyName='" + companyName + '\'' +
        ", department='" + department + '\'' +
        ", applicants=" + applicants +
        '}';
  }
}
