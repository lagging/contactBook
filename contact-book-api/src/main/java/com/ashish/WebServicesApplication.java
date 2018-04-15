package com.ashish;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication
@EnableAsync
@EnableAutoConfiguration
@ComponentScan("com.ashish.ws")
@EntityScan("com.ashish.ws.models")
@EnableJpaRepositories("com.ashish.ws.repository")
public class WebServicesApplication extends SpringBootServletInitializer {


  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(WebServicesApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(WebServicesApplication.class, args);
  }

  @PostConstruct
  void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
