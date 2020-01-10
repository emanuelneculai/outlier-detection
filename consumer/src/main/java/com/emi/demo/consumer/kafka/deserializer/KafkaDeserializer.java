package com.emi.demo.consumer.kafka.deserializer;

import com.emi.demo.kafka.model.sensor.data.KafkaSensorData;
import com.emi.demo.kafka.model.sensor.format.DateFormatConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class KafkaDeserializer implements Deserializer<KafkaSensorData> {

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(DateFormatConfig.SENSOR_DATE_FORMAT);
	}

	@Override
	public KafkaSensorData deserialize(String topic, byte[] message) {
		if (message == null) {
			return null;
		}

		try {
			return objectMapper.readValue(message,KafkaSensorData.class);
		} catch (IOException e) {
			//TODO Treat exception
			e.printStackTrace();
			return null;
		}
	}
}
