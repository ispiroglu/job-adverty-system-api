package com.lcwaikiki.advertservice.dto.response.user;

import com.lcwaikiki.advertservice.dto.model.advert.AdvertInfo;
import java.util.ArrayList;
import java.util.List;

public class UserApplicationsResponse {

  private List<AdvertInfo> advertInfos;

  public UserApplicationsResponse(List<AdvertInfo> advertInfos) {
    this.advertInfos = advertInfos;
  }

  public UserApplicationsResponse() {
    advertInfos = new ArrayList<>();
  }

  public List<AdvertInfo> getAdvertInfos() {
    return advertInfos;
  }

  public void addToAdvertInfos(AdvertInfo info) {
    advertInfos.add(info);
  }
}
