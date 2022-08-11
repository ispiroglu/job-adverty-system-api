package com.lcwaikiki.advertservice.data;

import com.github.javafaker.Faker;
import com.lcwaikiki.advertservice.model.Advert;
import com.lcwaikiki.advertservice.repository.AdvertRepository;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoader implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
  private final AdvertRepository advertRepository;
  private final Faker faker;

  public SampleDataLoader(AdvertRepository advertRepository, Faker faker) {
    this.advertRepository = advertRepository;
    this.faker = faker;
  }

  public void run(String... args) {
    logger.info("Loading Sample Data");

    List<Advert> list = IntStream.rangeClosed(1, 100)
        .mapToObj(i -> new Advert(
            faker.company().name(),
            faker.company().buzzword(),
            faker.date().future(10, 2, TimeUnit.DAYS),
            faker.date().future(60, 10, TimeUnit.DAYS),
            faker.job().position(), 100, faker.address().cityName(), faker.address().state(),
            10,
            faker.job().title(), true, null, faker.company().name(), faker.job().field()
        ))
        .toList();

    advertRepository.saveAll(list);
  }

}
