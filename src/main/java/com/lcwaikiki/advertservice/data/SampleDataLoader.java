package com.lcwaikiki.advertservice.data;

import com.github.javafaker.Faker;
import com.lcwaikiki.advertservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoader implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final Faker faker;

  public SampleDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder,
      Faker faker) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.faker = faker;
  }

  public void run(String... args) {
    logger.info("Loading Sample Data");

//    List<Advert> list = IntStream.rangeClosed(1, 100)
//        .mapToObj(i -> new Advert(
//            faker.company().name(),
//            faker.company().buzzword(),
//            faker.date().future(10, 2, TimeUnit.DAYS),
//            faker.date().future(60, 10, TimeUnit.DAYS),
//            faker.job().position(), 100, faker.address().cityName(), faker.address().state(),
//            10,
//            faker.job().title(), true, null, faker.company().name(), faker.job().field()
//        ))
//        .toList();

//    advertRepository.saveAll(list);
//    List<User> userList = userRepository.findAll();
//    userList.forEach(
//        user -> user.setPassword(passwordEncoder.encode(user.getPassword()))
//    );
//    userRepository.saveAll(userList);
  }

}
