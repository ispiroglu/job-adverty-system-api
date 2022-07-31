package com.lcwaikiki.advertservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI customOpenApi(@Value("${application-desc}") String desc,
      @Value("${application-version}") String version) {
    return new OpenAPI().info(
        new Info().title("LC Waikiki Advert API").version(version).description(desc)
            .license(new License().name("LC Waikiki Advert Api License"))
    );
  }
}
