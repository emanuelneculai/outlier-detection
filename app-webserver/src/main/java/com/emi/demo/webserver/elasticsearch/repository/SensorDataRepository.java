package com.emi.demo.webserver.elasticsearch.repository;

import com.emi.demo.elasticshearch.model.sensor.data.SensorData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SensorDataRepository extends ElasticsearchRepository<SensorData, Long> {
}