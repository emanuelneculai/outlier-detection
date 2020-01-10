package com.emi.demo.publisher.service;

import com.emi.demo.kafka.model.sensor.data.KafkaSensorData;
import com.emi.demo.publisher.model.SensorDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SensorDataPublishServiceImpl implements SensorDataPublishService {

	@Value("${kafka.sensor.topic}")
	private String KAFKA_SENSOR_TOPIC;

	@Autowired
	private KafkaTemplate<String, KafkaSensorData> kafkaTemplate;

	@Override
	public void publishSensorData(SensorDataDTO sensorDataDTO) {
		//TODO use dozer to map SensorDataDTO to KafkaSensorData;
		KafkaSensorData kafkaSensorData = KafkaSensorData.builder()
				.publisher(sensorDataDTO.getPublisher())
				.time(sensorDataDTO.getTime())
				.readings(sensorDataDTO.getReadings()).build();
		kafkaTemplate.send(KAFKA_SENSOR_TOPIC,kafkaSensorData);
		//TODO retrieve future response and log it
	}
}
