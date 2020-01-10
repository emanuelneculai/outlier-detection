package com.emi.demo.webserver.controller;

import com.emi.demo.webserver.model.SensorDataOutliersDTO;
import com.emi.demo.webserver.service.SensorDataOutliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class SensorDataOutliersController {

	@Autowired
	private SensorDataOutliersService sensorDataOutliersService;

	@RequestMapping(value = "/sensorData/outliers/{publisherId}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public SensorDataOutliersDTO getSensorDataOutliers(@PathVariable String publisherId, @RequestParam int numberOfReadings) {

		return sensorDataOutliersService.getSensorDataOutliersByPublisherIdAndNoOfReadings(publisherId, numberOfReadings);

	}

}
