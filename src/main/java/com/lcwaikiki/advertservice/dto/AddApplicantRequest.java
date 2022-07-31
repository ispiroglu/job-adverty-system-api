package com.lcwaikiki.advertservice.dto;

public class AddApplicantRequest {

  private Long userId;

  public AddApplicantRequest(Long userId) {
    this.userId = userId;
  }


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}