package com.emi.demo.webserver.service;

import com.emi.demo.webserver.model.SensorDataOutliersDTO;

public interface SensorDataOutliersService {

	SensorDataOutliersDTO getSensorDataOutliersByPublisherIdAndNoOfReadings(String publisherId, int noOfReadings);

}
