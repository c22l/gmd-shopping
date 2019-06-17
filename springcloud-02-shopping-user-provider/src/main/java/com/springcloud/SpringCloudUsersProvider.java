package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudUsersProvider {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudUsersProvider.class, args);

	}

}
