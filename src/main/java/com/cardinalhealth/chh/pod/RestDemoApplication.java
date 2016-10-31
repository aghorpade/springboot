package com.cardinalhealth.chh.pod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableJpaRepositories
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class RestDemoApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

	
}
