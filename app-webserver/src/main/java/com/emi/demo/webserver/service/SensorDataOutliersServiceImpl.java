package com.emi.demo.webserver.service;

import com.emi.demo.elasticshearch.model.sensor.data.SensorData;
import com.emi.demo.webserver.elasticsearch.repository.SensorDataRepository;
import com.emi.demo.webserver.model.SensorDataOutliersDTO;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class SensorDataOutliersServiceImpl implements SensorDataOutliersService {

	@Autowired
	private OutlierDetector outlierDetector;

	@Autowired
	private SensorDataRepository sensorDataRepository;

	@Override
	public SensorDataOutliersDTO getSensorDataOutliersByPublisherIdAndNoOfReadings(String publisherId, int noOfReadings) {
		final NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("publisher", publisherId))
				.withSort(SortBuilders.fieldSort("time").order(SortOrder.DESC)).withPageable(PageRequest.of(0, noOfReadings));

		List<SensorData> results = sensorDataRepository.search(searchQuery.build()).toList();
		double[] medians = new double[noOfReadings];
		for (int i = 0; i <= noOfReadings - 1; i++) {
			medians[i] = results.get(i).getMedian();
		}

		List<Double> medianOutliersList = new ArrayList<>();
		for (SensorData sensorData : results) {
			if (outlierDetector.isOutlier(medians, sensorData.getMedian())) {
				medianOutliersList.add(sensorData.getMedian());
			}
		}

		double[] medianOutliers = new double[medianOutliersList.size()];
		for (int i = 0; i <= medianOutliersList.size()-1; i++) {
			medianOutliers[i] = medianOutliersList.get(i);
		}

		return SensorDataOutliersDTO.builder().publisherId(publisherId).medians(medians).medianOutliers(medianOutliers).build();
	}

}
