package com.emi.demo.publisher.config;

import com.emi.demo.kafka.model.sensor.data.KafkaSensorData;
import com.emi.demo.publisher.serializer.KafkaSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

	@Value("${kafka.server.config}")
	private String BOOTSTRAP_SERVERS_CONFIG_VALUE;

	@Bean
	public ProducerFactory<String, KafkaSensorData> producerFactory() {
		Map<String, Object> kafkaConfigMap = new HashMap<>();
		kafkaConfigMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS_CONFIG_VALUE);
		kafkaConfigMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		kafkaConfigMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaSerializer.class);

		return new DefaultKafkaProducerFactory<>(kafkaConfigMap);
	}

	@Bean
	public KafkaTemplate<String, KafkaSensorData> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}

}
