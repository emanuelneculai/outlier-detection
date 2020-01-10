package com.emi.demo.publisher.controller;

import com.emi.demo.publisher.service.SensorDataPublishService;
import com.emi.demo.publisher.model.SensorDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class SensorDataPublishController {

	@Autowired
	private SensorDataPublishService sensorDataPublishService;

	@RequestMapping(value = "/sensorData",method = RequestMethod.POST,consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
	public void createSensorData(@RequestBody SensorDataDTO sensorData) {

		sensorDataPublishService.publishSensorData(sensorData);

	}

}