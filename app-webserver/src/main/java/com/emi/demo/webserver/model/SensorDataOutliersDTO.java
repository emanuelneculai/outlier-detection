package com.emi.demo.webserver.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorDataOutliersDTO {

	private String publisherId;
	private double[] medians;
	private double[] medianOutliers;

}
