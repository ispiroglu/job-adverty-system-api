package com.lcwaikiki.advertservice.repository;

import com.lcwaikiki.advertservice.filter.AdvertSpecification;
import com.lcwaikiki.advertservice.model.Advert;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long>,
    JpaSpecificationExecutor<AdvertSpecification> {


  List<Advert> findAdvertsByActive(boolean active);

  List<Advert> findAdvertsByPositionContainingIgnoreCase(String position);

  List<Advert> findAdvertsByProvinceContainingIgnoreCase(String province);


  @Query(value = "Select * from advert where province like (%:incomingProvince%) and"
      + " position like (%:incomingPosition%) and"
      + " advert.department like (%:incomingDepartment%) and"
      + "( name like (%:incomingText%) or summary like (%:incomingText%)) and advert.active", nativeQuery = true)
  List<Advert> findAdvertsByFullFilter(String incomingProvince, String incomingPosition,
      String incomingDepartment, String incomingText);

  @Query(value = "Select * from advert where advert.start_date < :date ", nativeQuery = true)
  List<Advert> findStartingAdverts(Date date);

  @Query(value = "Select * from advert where advert.end_date < :date and advert.active", nativeQuery = true)
  List<Advert> findEndingAdverts(Date date);
}
