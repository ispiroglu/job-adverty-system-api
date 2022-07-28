package com.lcwaikiki.advertservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdvertNotFoundException extends Exception {

  public AdvertNotFoundException() {
    super("Advert Not Found");
  }
}
