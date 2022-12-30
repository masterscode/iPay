package com.Index;

import com.Index.configs.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableConfigurationProperties(ConfigProperties.class)
@SpringBootApplication
public class IndexApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndexApplication.class, args);
	}

}
