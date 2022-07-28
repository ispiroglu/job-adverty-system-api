package com.lcwaikiki.advertservice.repository;

import com.lcwaikiki.advertservice.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

}
