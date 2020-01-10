package com.emi.demo.publisher.model;

import lombok.Data;

import java.util.Date;

@Data
public class SensorDataDTO {

	private String publisher;
	private Date time;
	private double[] readings;

}
