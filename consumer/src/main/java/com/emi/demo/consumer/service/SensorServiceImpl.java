package com.emi.demo.consumer.service;

import com.emi.demo.consumer.elasticshearch.repository.SensorDataRepository;
import com.emi.demo.elasticshearch.model.sensor.data.SensorData;
import com.emi.demo.kafka.model.sensor.data.KafkaSensorData;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {

	@Autowired
	private SensorDataRepository sensorDataRepository;

	@Override
	public void processSensorData(KafkaSensorData kafkaSensorData) {

		sensorDataRepository.save(SensorData.builder()
		.publisher(kafkaSensorData.getPublisher())
		.time(kafkaSensorData.getTime())
		.median(new Median().evaluate(kafkaSensorData.getReadings())).build());

	}

}
