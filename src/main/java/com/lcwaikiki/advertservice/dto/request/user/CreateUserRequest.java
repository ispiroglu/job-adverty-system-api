package com.lcwaikiki.advertservice.dto.request.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateUserRequest {

  @NotNull(message = "isEmployer cannot be null")
  private boolean isEmployer;
  @NotNull(message = "email cannot be null")
  @NotEmpty(message = "email cannot be Empty")
  private String email;
  @NotNull(message = "Password cannot be Null")
  @NotEmpty(message = "Password cannot be Empty")
  private String password;

  public CreateUserRequest(boolean isEmployer, String email, String password) {
    this.isEmployer = isEmployer;
    this.email = email;
    this.password = password;
  }

  public boolean isEmployer() {
    return isEmployer;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
