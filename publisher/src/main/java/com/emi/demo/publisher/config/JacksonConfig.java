package com.emi.demo.publisher.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.emi.demo.kafka.model.sensor.format.DateFormatConfig.SENSOR_DATE_FORMAT;

@Configuration
public class JacksonConfig {

	@Bean
	public ObjectMapper objectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(SENSOR_DATE_FORMAT);
		return objectMapper;
	}

}
