package com.lcwaikiki.advertservice.repository;

import com.lcwaikiki.advertservice.filter.AdvertSpecification;
import com.lcwaikiki.advertservice.model.Advert;
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
      + "( name like (%:incomingText%) or summary like (%:incomingText%))", nativeQuery = true)
  List<Advert> findAdvertsByFullFilter(String incomingProvince, String incomingPosition,
      String incomingDepartment, String incomingText);


}
