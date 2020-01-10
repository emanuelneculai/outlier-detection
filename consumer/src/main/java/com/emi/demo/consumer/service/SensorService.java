package com.emi.demo.consumer.service;

import com.emi.demo.kafka.model.sensor.data.KafkaSensorData;

public interface SensorService {

	void processSensorData(KafkaSensorData kafkaSensorData);

}
