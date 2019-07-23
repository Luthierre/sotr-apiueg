package br.ueg.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.ueg.api.config.property.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class SotrUegApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SotrUegApiApplication.class, args);
	}
}
