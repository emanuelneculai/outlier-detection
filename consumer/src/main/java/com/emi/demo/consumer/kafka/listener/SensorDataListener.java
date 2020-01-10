package com.emi.demo.consumer.kafka.listener;

import com.emi.demo.consumer.service.SensorService;
import com.emi.demo.kafka.model.sensor.data.KafkaSensorData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SensorDataListener {

	@Autowired
	private SensorService sensorService;

	@KafkaListener(topics = "sensor", groupId = "sensor_groupid")
	public void consume(KafkaSensorData sensorData) throws JsonProcessingException {
		sensorService.processSensorData(sensorData);
	}

}
