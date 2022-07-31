package com.lcwaikiki.advertservice.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ApplicationDetail {

  @Id
  @GeneratedValue
  private Long id;


  @ManyToOne
  @JoinColumn(name = "advert_id")
//  @JsonBackReference
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  private Advert advert;

  @ManyToOne
  @JoinColumn(name = "user_id")
//  @JsonBackReference
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  private User user;

  private ApplicationStatus status;

  public ApplicationDetail(Advert advert, User user) {
    this.advert = advert;
    this.user = user;
    status = ApplicationStatus.PENDING;
  }

  public ApplicationDetail() {
  }


  public Advert getAdvert() {
    return advert;
  }

  public void setAdvert(Advert advert) {
    this.advert = advert;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public ApplicationStatus getStatus() {
    return status;
  }

  public void setStatus(ApplicationStatus status) {
    this.status = status;
  }

  public Long getId() {
    return id;
  }
}
