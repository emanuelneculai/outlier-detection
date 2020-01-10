package com.emi.demo.consumer.config;

import com.emi.demo.consumer.kafka.deserializer.KafkaDeserializer;
import com.emi.demo.kafka.model.sensor.data.KafkaSensorData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

	@Value("${kafka.server.config}")
	private String BOOTSTRAP_SERVERS_CONFIG_VALUE;

	@Value("${kafka.consumer.groupid}")
	private String GROUP_ID_CONFIG_VALUE;

	@Bean
	public ConsumerFactory<String, KafkaSensorData> consumerFactory() {
		Map<String, Object> kafkaConfigMap = new HashMap<>();
		kafkaConfigMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS_CONFIG_VALUE);
		kafkaConfigMap.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID_CONFIG_VALUE);
		kafkaConfigMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		kafkaConfigMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(kafkaConfigMap);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaSensorData> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaSensorData> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());

		return concurrentKafkaListenerContainerFactory;
	}

}
