package com.lcwaikiki.advertservice.dto.response.advert;

import com.lcwaikiki.advertservice.dto.model.advert.DashboardAdvertTableInfoDto;
import java.util.List;

public class DashboardInfoResponse {

  private Long totalAdvertCount;
  private Long totalUserCount;
  private Long totalEventCount;
  private List<DashboardAdvertTableInfoDto> soonEndingAdverts;
  private List<DashboardAdvertTableInfoDto> soonStartingAdverts;

  public DashboardInfoResponse(Long totalAdvertCount, Long totalUserCount, Long totalEventCount,
      List<DashboardAdvertTableInfoDto> soonEndingAdverts,
      List<DashboardAdvertTableInfoDto> soonStartingAdverts) {
    this.totalAdvertCount = totalAdvertCount;
    this.totalUserCount = totalUserCount;
    this.totalEventCount = totalEventCount;
    this.soonEndingAdverts = soonEndingAdverts;
    this.soonStartingAdverts = soonStartingAdverts;
  }

  public Long getTotalAdvertCount() {
    return totalAdvertCount;
  }

  public Long getTotalUserCount() {
    return totalUserCount;
  }

  public Long getTotalEventCount() {
    return totalEventCount;
  }

  public List<DashboardAdvertTableInfoDto> getSoonEndingAdverts() {
    return soonEndingAdverts;
  }

  public List<DashboardAdvertTableInfoDto> getSoonStartingAdverts() {
    return soonStartingAdverts;
  }
}
