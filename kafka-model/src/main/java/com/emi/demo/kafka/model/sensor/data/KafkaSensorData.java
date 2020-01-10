package com.emi.demo.kafka.model.sensor.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaSensorData {

	private String publisher;
	private Date time;
	private double[] readings;

}
