package com.comparefolder.CompareApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.comparefolder.CompareApplication.properties.StorageProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class CompareApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompareApplication.class, args);
	}

}
