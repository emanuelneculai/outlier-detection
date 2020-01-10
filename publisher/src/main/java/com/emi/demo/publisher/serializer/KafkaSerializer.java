package com.emi.demo.publisher.serializer;

import com.emi.demo.kafka.model.sensor.data.KafkaSensorData;
import com.emi.demo.kafka.model.sensor.format.DateFormatConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class KafkaSerializer implements Serializer<KafkaSensorData> {

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(DateFormatConfig.SENSOR_DATE_FORMAT);
	}

	@Override
	public byte[] serialize(String topic, KafkaSensorData message) {
		if (message == null) {
			return null;
		}

		try {
			return objectMapper.writeValueAsBytes(message);
		} catch (JsonProcessingException e) {
			//TODO Treat exception
			e.printStackTrace();
			return null;
		}
	}
}
