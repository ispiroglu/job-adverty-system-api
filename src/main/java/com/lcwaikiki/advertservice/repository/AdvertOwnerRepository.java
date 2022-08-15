package com.lcwaikiki.advertservice.repository;

import com.lcwaikiki.advertservice.model.AdvertOwner;
import com.lcwaikiki.advertservice.model.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertOwnerRepository extends JpaRepository<AdvertOwner, Long> {

  Page<AdvertOwner> findAdvertOwnersByUser(User user, PageRequest pageRequest);

  List<AdvertOwner> findAdvertOwnersByUser(User user);
}
