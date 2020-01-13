package com.emi.demo.publisher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorDataDTO {

	private String publisher;
	private Date time;
	private double[] readings;

}
